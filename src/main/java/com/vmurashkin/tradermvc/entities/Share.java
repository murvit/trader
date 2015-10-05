package com.vmurashkin.tradermvc.entities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.persistence.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by OG_ML on 17.08.2015.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Share {

    @Id
    @GeneratedValue
    private int id;
    private String ticker;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @Transient
    private String name;
    @Transient
    private BigDecimal ask;
    @Transient
    private BigDecimal bid;
    @Transient
    private BigDecimal yearLow;
    @Transient
    private BigDecimal yearHigh;
    @Transient
    private String marketCapitalization;
    @Transient
    private BigDecimal oneYearTargetPrice;

    // getting share data from yahoo finance
    public JsonObject getJson() {
        String request = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20" +
                "from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" +
                this.getTicker() +
                "%22)%20&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        String json = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
            HttpGet httpRequest = new HttpGet(request);
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            json = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("query");
        jobject = jobject.getAsJsonObject("results");
        jobject = jobject.getAsJsonObject("quote");
        return jobject;
    }

    public void getAllData() {
        JsonObject jobject = this.getJson();
        try{
        this.setName(jobject.getAsJsonPrimitive("Name").getAsString());
        this.setAsk(new BigDecimal(jobject.getAsJsonPrimitive("Ask").getAsString()));
        this.setBid(new BigDecimal(jobject.getAsJsonPrimitive("Bid").getAsString()));
        this.setYearLow(new BigDecimal(jobject.getAsJsonPrimitive("YearLow").getAsString()));
        this.setYearHigh(new BigDecimal(jobject.getAsJsonPrimitive("YearHigh").getAsString()));
        this.setMarketCapitalization(jobject.getAsJsonPrimitive("MarketCapitalization").getAsString());
        this.setOneYearTargetPrice(new BigDecimal(jobject.getAsJsonPrimitive("OneyrTargetPrice").getAsString()));}
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getCurrentBid() {
        JsonObject jobject = this.getJson();
        return new BigDecimal(jobject.getAsJsonPrimitive("Bid").getAsString());
    }

    public BigDecimal getCurrentAsk() {
        JsonObject jobject = this.getJson();
        return new BigDecimal(jobject.getAsJsonPrimitive("Ask").getAsString());
    }

    public Share() {
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getYearLow() {
        return yearLow;
    }

    public void setYearLow(BigDecimal yearLow) {
        this.yearLow = yearLow;
    }

    public BigDecimal getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(BigDecimal yearHigh) {
        this.yearHigh = yearHigh;
    }

    public String getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(String marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public BigDecimal getOneYearTargetPrice() {
        return oneYearTargetPrice;
    }

    public void setOneYearTargetPrice(BigDecimal oneYearTargetPrice) {
        this.oneYearTargetPrice = oneYearTargetPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Share(String ticker, String name, BigDecimal ask,
                 BigDecimal bid, BigDecimal yearLow, BigDecimal yearHigh,
                 String marketCapitalization, BigDecimal oneYearTargetPrice) {
        this.ticker = ticker;
        this.name = name;
        this.ask = ask;
        this.bid = bid;
        this.yearLow = yearLow;
        this.yearHigh = yearHigh;
        this.marketCapitalization = marketCapitalization;
        this.oneYearTargetPrice = oneYearTargetPrice;
    }

    public Share(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "Share{" +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", user=" + user.getName() +
                ", name='" + name + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", yearLow=" + yearLow +
                ", yearHigh=" + yearHigh +
                ", marketCapitalization='" + marketCapitalization + '\'' +
                ", oneYearTargetPrice=" + oneYearTargetPrice +
                '}';
    }
}
