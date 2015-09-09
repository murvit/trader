package com.vmurashkin.tradermvc.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by OG_ML on 18.08.2015.
 */

//@Entity
public class Transaction {
//    @Id
//    @GeneratedValue
    private long id;
//    @ManyToOne
//    @JoinColumn(name="user_id")
    private User user;
    private Date time;
    private Share share;
    private BigDecimal price;
    private int numberOfShares;
    private boolean buy;

    public Transaction() {
    }
}
