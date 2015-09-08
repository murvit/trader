package com.vmurashkin.tradermvc.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
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

//    private String login;
//    private String password;

//    @OneToOne(cascade= CascadeType.ALL)
//    @JoinColumn(name="portfolio_id")
//    private Portfolio portfolio;

//    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Share> shares = new ArrayList<Share>();

    private BigDecimal money;

    public void addShare(Share share) {
        shares.add(share);
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
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

    public User() {
        this.money = new BigDecimal(100000);
    }
}
