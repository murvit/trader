package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by OG_ML on 07.09.2015.
 */

@Controller
@RequestMapping("/")
public class MainController {


    private TraderDAO traderDAO = new TraderDAOImpl();

    ShareManipulator manipulator = new ShareManipulatorImpl();
    private User user = manipulator.getUser(1);

    @RequestMapping("/")
    public ModelAndView listAdvs() {
        return new ModelAndView("index", "shares", traderDAO.getShareList(user));
    }


}
