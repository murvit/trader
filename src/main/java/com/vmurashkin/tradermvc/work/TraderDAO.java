package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;

import java.util.List;

/**
 * Trader DAO
 */
public interface TraderDAO {
    User getCurrentUser();

    boolean isUserExist(String name);

    Share getShareById(int id);

    Share getShareByTicker(User user, String ticker);

    List<Share> getWatchShareListByUser(User user);

    void addUser(User user);

    boolean buyShares(User user, String ticker, int quantity);

    boolean sellShares(User user, String ticker, int quantity);

    void removeTicker(User user, String ticker);

    void restoreTickers(User user);
}
