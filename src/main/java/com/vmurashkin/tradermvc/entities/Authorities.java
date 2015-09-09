package com.vmurashkin.tradermvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by OG_ML on 09.09.2015.
 */

@Entity
public class Authorities {

    @Id
    String username;

    String authority;

}
