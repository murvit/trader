package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
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
    @Autowired
    private TraderDAO traderDAO;// = new TraderDAOImpl();

    @RequestMapping("/")
    public ModelAndView listShares() {
        User user = traderDAO.getUser(1);
        List<Share> shares = traderDAO.getShareList(user);
        for (Share share : shares) {
            share.getAllData();
        }
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("shares", shares);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/buy")
    public ModelAndView buyShares() {
        User user = traderDAO.getUser(1);
        ModelAndView modelAndView = new ModelAndView("buy");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
