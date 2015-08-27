package com.vmurashkin.tradermvc.model;

import javax.persistence.*;

/**
 * Created by OG_ML on 17.08.2015.
 */
@Entity
public class Share {

    @Id
    @GeneratedValue
    private int id;
    private String ticker;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


//    private String name;
//    private double ask;
//    private double bid;
//    private double yearLow;
//    private double yearHigh;
//    private String marketCapitalization;
//    private double oneYearTargetPrice;

    public Share() {
    }

//    public Share(String ticker, String name, double ask, double bid, double yearLow,
//                 double yearHigh, String marketCapitalization, double oneYearTargetPrice) {
//        this.ticker = ticker;
//        this.name = name;
//        this.ask = ask;
//        this.bid = bid;
//        this.yearLow = yearLow;
//        this.yearHigh = yearHigh;
//        this.marketCapitalization = marketCapitalization;
//        this.oneYearTargetPrice = oneYearTargetPrice;
//    }


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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getAsk() {
//        return ask;
//    }
//
//    public void setAsk(double ask) {
//        this.ask = ask;
//    }
//
//    public double getBid() {
//        return bid;
//    }
//
//    public void setBid(double bid) {
//        this.bid = bid;
//    }
//
//    public double getYearLow() {
//        return yearLow;
//    }
//
//    public void setYearLow(double yearLow) {
//        this.yearLow = yearLow;
//    }
//
//    public double getYearHigh() {
//        return yearHigh;
//    }
//
//    public void setYearHigh(double yearHigh) {
//        this.yearHigh = yearHigh;
//    }
//
//    public String getMarketCapitalization() {
//        return marketCapitalization;
//    }
//
//    public void setMarketCapitalization(String marketCapitalization) {
//        this.marketCapitalization = marketCapitalization;
//    }
//
//    public double getOneYearTargetPrice() {
//        return oneYearTargetPrice;
//    }
//
//    public void setOneYearTargetPrice(double oneYearTargetPrice) {
//        this.oneYearTargetPrice = oneYearTargetPrice;
//    }
//
//    @Override
//    public String toString() {
//        return "Share{" +
//                "ticker='" + ticker + '\'' +
//                ", name='" + name + '\'' +
//                ", bid=" + bid +
//                ", ask=" + ask +
//                ", yearLow=" + yearLow +
//                ", yearHigh=" + yearHigh +
//                ", marketCapitalization='" + marketCapitalization + '\'' +
//                ", oneYearTargetPrice=" + oneYearTargetPrice +
//                '}';
//    }
}
