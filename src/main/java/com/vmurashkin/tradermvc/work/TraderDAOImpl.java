package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Trader DAO impl
 */

public class TraderDAOImpl implements TraderDAO {

    @PersistenceContext //(type=PersistenceContextType.EXTENDED)
            EntityManager em;

    @Override
    public Share getShareById(int id) {
        return em.find(Share.class, id);
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean buyShares(User user, String ticker, int quantity) {

        Share share = new Share(ticker);
        share.setAsk(share.getCurrentAsk());
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
                int newQuantity = current.getQuantity() - quantity;
                if (newQuantity < 0) return false;
                BigDecimal newMoney = user.getMoney().add(current.getCurrentBid().multiply(new BigDecimal(quantity)));
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

    public TraderDAOImpl() {
    }

    @Override
    @Transactional
    public void removeTicker(User user, String ticker) {
        List<String> tickers = user.getTickers();
        tickers.remove(ticker);
        user.setTickers(tickers);
        em.merge(user);
    }

    @Override
    @Transactional
    public void restoreTickers(User user) {
        user.setTickers();
        em.merge(user);
    }
}
