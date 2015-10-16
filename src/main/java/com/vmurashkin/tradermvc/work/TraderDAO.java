package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;

import java.util.List;

/**
 * Trader DAO
 */
public interface TraderDAO {
    User getCurrentUser();
    public boolean isUserExist(String name);
    Share getShareById(int id);
    Share getShareByTicker (User user, String ticker);
    List<Share> getShareListByUser(User user);
    List<Share> getWatchShareListByUser (User user);
    void addUser(User user);
    boolean buyShares (User user, String ticker, int quantity);
    void sellShares (User user, String ticker, int quantity);
    void closeAll();
}
