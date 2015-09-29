package com.vmurashkin.tradermvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by OG_ML on 09.09.2015.
 */

@Entity (name = "user_roles")
public class UserRoles {

    @Id
    String username;

    String role;

}
