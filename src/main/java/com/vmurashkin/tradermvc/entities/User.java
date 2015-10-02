package com.vmurashkin.tradermvc.entities;

import org.hibernate.annotations.IndexColumn;
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
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    private String role;

    private int enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Share> shares = new ArrayList<>();

    @ElementCollection
    private List<String> tickers = new ArrayList<>();

    private BigDecimal money;

    @Transient
    private BigDecimal sum;

    public String getName() {
        return name;
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

    public User() {
        this.money = new BigDecimal(100000);
        this.tickers = Arrays.asList("AAPL", "GOOG", "FB", "MSFT", "EBAY", "MCD", "KO", "PEP", "GE",
                "BA", "JNJ", "PFE", "XOM", "CVX", "T", "BAC", "JPM");

    }
}
