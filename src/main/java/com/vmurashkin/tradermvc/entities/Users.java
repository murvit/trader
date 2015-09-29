package com.vmurashkin.tradermvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by OG_ML on 28.09.2015.
 */

@Entity
public class Users {
    @Id
    String username;
    String password;


}
