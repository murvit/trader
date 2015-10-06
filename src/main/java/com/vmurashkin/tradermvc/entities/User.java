package com.vmurashkin.tradermvc.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String name;

    private String password;

    private String role;

    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Share> shares = new ArrayList<>();

    @ElementCollection
    private List<String> tickers = new ArrayList<>();

    private BigDecimal money;

    @Transient
    private BigDecimal sum;

    public void addShare (Share share){

        this.shares.add(share);

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

    public void countSum() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Share share : shares) {
            share.getAllData();
            sum = sum.add(share.getBid().multiply(new BigDecimal(share.getQuantity())));
        }
        this.sum=sum.add(this.getMoney());
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.money = new BigDecimal(100000);
//        this.tickers = Arrays.asList("AAPL", "GOOG", "FB", "MSFT", "EBAY", "MCD", "KO", "PEP", "GE",
//                "BA", "JNJ", "PFE", "XOM", "CVX", "T", "BAC", "JPM");

        this.tickers = Arrays.asList("AAPL", "GOOG");
        this.enabled = true;
        this.role = "ROLE_USER";
    }

    public User() {
    }
}
