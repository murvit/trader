package com.vmurashkin.tradermvc.work;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Trader DAO impl
 */

public class TraderDAOImpl implements TraderDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Share getShareByTicker(User user, String ticker) {
        for (Share current : user.getShares()) {
            if (current.getTicker().equals(ticker)) {
                return current;
            }
        }
        return new Share(ticker);
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        User user;
        String name;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            name = ((UserDetails) principal).getUsername();
        } else {
            name = principal.toString();
        }
        user = em.find(User.class, name);
        return user;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        em.merge(user);
    }

    @Override
    @Transactional
    public boolean isUserExist(String name) {
        if (name != null && !"".equals(name)) {
            User user = em.find(User.class, name);
            return (user != null);
        } else return true;
    }

    @Override
    public List<Share> getWatchShareListByUser(User user) {
        List<Share> shares = new ArrayList<>();
        for (String ticker : user.getTickers()) {
            Share share = new Share(ticker);
            shares.add(share);
        }
        return shares;
    }

    @Override
    public void countSum(User user, List<Share> shares) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Share share : shares) {
            if (share.getBid() != null)
                sum = sum.add(share.getBid().multiply(new BigDecimal(share.getQuantity())));
        }
        sum = sum.add(user.getMoney());
        user.setSum(sum);
    }

    @Override
    @Transactional
    public boolean buyShares(User user, String ticker, int quantity) {

        Share share = new Share(ticker);
        setShareData(share);
        share.setQuantity(quantity);
        share.setUser(user);

        BigDecimal newMoney = user.getMoney().subtract(share.getAsk().multiply(new BigDecimal(quantity)));
        if (newMoney.compareTo(new BigDecimal(0)) <= 0 || quantity <= 0) return false;

        boolean isPresent = false;
        for (Share current : user.getShares()) {
            if (current.getTicker().equals(ticker)) {
                user.setMoney(newMoney);
                current.setQuantity(current.getQuantity() + quantity);
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            user.setMoney(newMoney);
            user.addShare(share);
        }
        em.merge(user);
        return true;
    }

    @Override
    @Transactional
    public boolean sellShares(User user, String ticker, int quantity) {

        List<Share> shares = user.getShares();
        Iterator<Share> iterator = shares.iterator();
        boolean isPresent = false;
        while (iterator.hasNext()) {
            Share current = iterator.next();
            if (current.getTicker().equals(ticker)) {
                isPresent = true;
                setShareData(current);
                int newQuantity = current.getQuantity() - quantity;
                if (newQuantity < 0) return false;
                BigDecimal newMoney = user.getMoney().add(current.getBid().multiply(new BigDecimal(quantity)));
                if (newQuantity == 0) {
                    iterator.remove();
                } else {
                    current.setQuantity(newQuantity);
                }
                user.setMoney(newMoney);
                break;
            }
        }
        if (!isPresent) return false;
        em.merge(user);
        return true;
    }

    @Override
    @Transactional
    public void removeTicker(User user, String ticker) {
        user.getTickers().remove(ticker);
        em.merge(user);
    }

    @Override
    @Transactional
    public void restoreTickers(User user) {
        user.setTickers();
        em.merge(user);
    }

    @Override
    public void setData(List<Share> shares) {
        if (shares.size() < 1) return;
        if (shares.size() == 1)
            setShareData(shares.get(0));
        else
            setSharesData(shares);
    }

    @Override
    public void setShareData(Share share) {
        List<Share> shares = Arrays.asList(share);
        JsonObject jsonObject = getJsonObject(shares);
        if (jsonObject != null) {
            jsonObject = jsonObject.getAsJsonObject("quote");
            setShareDataFromJsonObject(jsonObject, share);
        }
    }

    public void setSharesData(List<Share> shares) {
        JsonObject jsonObject = getJsonObject(shares);
        if (jsonObject != null) {
            JsonArray jsonArray = jsonObject.getAsJsonArray("quote");
            for (JsonElement jsonElement : jsonArray) {
                jsonObject = jsonElement.getAsJsonObject();
                try {
                    for (Share share : shares) {
                        if (jsonObject.getAsJsonPrimitive("Symbol").getAsString().equals(share.getTicker())) {
                            setShareDataFromJsonObject(jsonObject, share);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setShareDataFromJsonObject(JsonObject jsonObject, Share share) {
        share.setTicker(jsonObject.getAsJsonPrimitive("Symbol").getAsString());
        share.setName(jsonObject.getAsJsonPrimitive("Name").getAsString());
        JsonElement je = jsonObject.getAsJsonPrimitive("Ask");
        if (!je.isJsonNull())
            share.setAsk(new BigDecimal(je.getAsString()));
        je = jsonObject.getAsJsonPrimitive("Bid");
        if (!je.isJsonNull())
            share.setBid(new BigDecimal(je.getAsString()));
        share.setYearLow(new BigDecimal(jsonObject.getAsJsonPrimitive("YearLow").getAsString()));
        share.setYearHigh(new BigDecimal(jsonObject.getAsJsonPrimitive("YearHigh").getAsString()));
        share.setMarketCapitalization(jsonObject.getAsJsonPrimitive("MarketCapitalization").getAsString());
        share.setOneYearTargetPrice(new BigDecimal(jsonObject.getAsJsonPrimitive("OneyrTargetPrice").getAsString()));
    }

    public JsonObject getJsonObject(List<Share> shares) {

        String request = setRequest(shares);
        String json = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpRequest = new HttpGet(request);
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            json = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (json != null && !json.equals("")) {
            JsonElement jsonElement = new JsonParser().parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            jsonObject = jsonObject.getAsJsonObject("query");
            jsonObject = jsonObject.getAsJsonObject("results");
            return jsonObject;
        }
        return null;
    }

    public String setRequest(List<Share> shares) {
        StringBuilder requestSB = new StringBuilder("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(");
        for (Share share : shares) {
            requestSB.append("%22").append(share.getTicker()).append("%22,");
        }
        requestSB.setLength(requestSB.length() - 1);
        requestSB.append(")%20&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        return requestSB.toString();
    }
}