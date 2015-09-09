package com.vmurashkin.tradermvc.entities;

/**
 * Created by OG_ML on 18.08.2015.
 */
//@Entity
public class Portfolio {
//    @Id
//    @GeneratedValue
    private long id;
//    @ManyToOne
//    @JoinColumn(name="user_id")
    private User user;
//    @OneToMany
//    @JoinColumn(name="share_id")
    private Share share;
    private int quantity;
//    private BigDecimal money;
//
//    public Portfolio() {
//        money = BigDecimal.valueOf(100000);
//    }

}
