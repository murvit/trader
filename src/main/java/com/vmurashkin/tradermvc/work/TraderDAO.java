package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;

import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */
public interface TraderDAO {
    User getUser(int id);
    User getCurrentUser();
    List<Share> getShareListByUser(User user);
    List<Share> getShareListByTickers (List<String>tickers);
    void buyShares (User user, String ticker, int quantity);
    void sellShares (User user, String ticker, int quantity);
    void closeAll();

}
