package com.vmurashkin.tradermvc.entities;

/**
 * for future
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
