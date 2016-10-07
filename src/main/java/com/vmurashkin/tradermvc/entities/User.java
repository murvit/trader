package com.vmurashkin.tradermvc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User instance
 */

@Entity
public class User implements Serializable{

    @Id
    private String name;

    private String password;

    private String role;

    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Share> shares = new ArrayList<>();

    @ElementCollection
    private List<String> tickers = new ArrayList<>();

    private BigDecimal money;

    @Transient
    private BigDecimal sum;

    public void addShare (Share share){

        this.shares.add(share);
    }

    public void removeShare (Share share) {
        this.shares.remove(share);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.money = new BigDecimal(100000.00);
        this.setTickers();
        this.enabled = true;
        this.role = "ROLE_USER";
    }

    public User() {
    }

    public void setTickers(){
        this.tickers = new LinkedList<>(Arrays.asList("AAPL", "GOOG", "FB", "MSFT", "AMZN", "MCD", "KO", "PEP", "GE",
                "BA", "JNJ", "PFE", "XOM", "CVX", "T", "BAC", "JPM"));
    }

}
