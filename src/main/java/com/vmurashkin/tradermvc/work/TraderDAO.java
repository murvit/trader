package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;

import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */
public interface TraderDAO {
    User getCurrentUser();
    Share getShareById(int id);
    List<Share> getShareListByUser(User user);
    List<Share> getWatchShareListByUser (User user);
    void buyShares (User user, String ticker, int quantity);
    void sellShares (User user, String ticker, int quantity);
    void closeAll();

}
