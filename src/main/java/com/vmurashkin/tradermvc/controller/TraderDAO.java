package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.Share;
import com.vmurashkin.tradermvc.model.User;

import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */
public interface TraderDAO {
    List<Share> getShareList (User user);
}
