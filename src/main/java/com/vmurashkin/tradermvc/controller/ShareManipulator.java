package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.Share;
import com.vmurashkin.tradermvc.model.User;

import java.util.List;

/**
 * Created by OG_ML on 27.08.2015.
 */

public interface ShareManipulator {

    User getUser(int id);
    void buyShares (User user, String ticker, int quantity);
    void sellShares (User user, String ticker, int quantity);
    void closeAll();
}
