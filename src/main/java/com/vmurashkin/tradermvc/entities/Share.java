package com.vmurashkin.tradermvc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Share instance
 */

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class Share implements Serializable{

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
