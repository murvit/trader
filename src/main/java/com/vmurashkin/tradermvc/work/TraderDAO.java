package com.vmurashkin.tradermvc.work;

import com.google.gson.JsonObject;
import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;

import java.util.List;

/**
 * Trader DAO
 */

public interface TraderDAO {
    User getCurrentUser();

    boolean isUserExist(String name);

    Share getShareByTicker(User user, String ticker);

    List<Share> getWatchShareListByUser(User user);

    void addUser(User user);

    void countSum(User user, List<Share> shares);

    boolean buyShares(User user, String ticker, int quantity);

    boolean sellShares(User user, String ticker, int quantity);

    void removeTicker(User user, String ticker);

    void restoreTickers(User user);

    void setData(List<Share> shares);

    void setShareData(Share share);

}
