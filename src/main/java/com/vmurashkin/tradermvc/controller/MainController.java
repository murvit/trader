package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.Share;
import com.vmurashkin.tradermvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */

@Controller
@RequestMapping("/")
public class MainController {

    private TraderDAO traderDAO = new TraderDAOImpl();
    private User user = traderDAO.getUser(1);

    @RequestMapping("/")
    public ModelAndView listShares() {
        List<Share> shares = traderDAO.getShareList(user);
        for (Share share : shares) {
            share.getAllData();
        }
        return new ModelAndView("hello", "shares", shares);
    }

}
