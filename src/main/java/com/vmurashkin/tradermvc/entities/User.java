package com.vmurashkin.tradermvc.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by OG_ML on 18.08.2015.
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String role;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Share> shares = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Share> watchShares = new ArrayList<>();

    private BigDecimal money;

    @Transient
    private BigDecimal sum;

    public void addShare(Share share) {
        shares.add(share);
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public List<Share> getWatchShares() {
        return watchShares;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWatchShares(List<Share> watchShares) {
        this.watchShares = watchShares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User() {
        this.money = new BigDecimal(100000);
        List<String> tickers = Arrays.asList("AAPL", "GOOG", "FB", "MSFT", "EBAY", "MCD", "KO", "PEP", "GE",
                "BA", "JNJ", "PFE", "XOM", "CVX", "T", "BAC", "JPM");
        for (String ticker : tickers){
            this.watchShares.add(new Share(ticker));
        }
    }
}
