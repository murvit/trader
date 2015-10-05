package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private TraderDAO traderDAO;// = new TraderDAOImpl();

    @RequestMapping({"/", "/hello"})
    public ModelAndView listShares() {

        User user = traderDAO.getCurrentUser();
        List<Share> shares = traderDAO.getShareListByUser(user);
        user.countSum();
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("shares", shares);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping("/sign")
    public ModelAndView sign() {
        return new ModelAndView("sign");
    }

    @RequestMapping("/adduser")
    public String addUser(@RequestParam(value = "name") String name,
                          @RequestParam(value = "password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        if (!traderDAO.isUserExist(name)) {
            User user = new User(name, password);
            traderDAO.addUser(user);
            return "redirect:/login?signed";
        }
        return "redirect:/sign?error";
    }

    @RequestMapping("/analytic")
    public ModelAndView analytic() {

        User user = traderDAO.getCurrentUser();
        List<Share> shares = traderDAO.getWatchShareListByUser(user);

        for (Share share : shares) {
            share.getAllData();
            System.out.println(share.getTicker());
        }

        ModelAndView modelAndView = new ModelAndView("analytic");
        modelAndView.addObject("shares", shares);
        return modelAndView;
    }

    @RequestMapping("/buy")
    public ModelAndView buyShares(@RequestParam(value = "ticker") String ticker) {
        User user = traderDAO.getCurrentUser();
//        Share share = traderDAO.getShareByTicker(user, ticker);
        ModelAndView modelAndView = new ModelAndView("buy");
        modelAndView.addObject("user", user);
        modelAndView.addObject("ticker", ticker);
        return modelAndView;
    }

    @RequestMapping("/sell")
    public ModelAndView sellShares(@RequestParam(value = "id") int id) {
        User user = traderDAO.getCurrentUser();
        Share share = traderDAO.getShareById(id);
        ModelAndView modelAndView = new ModelAndView("sell");
        modelAndView.addObject("user", user);
        modelAndView.addObject("share", share);
        return modelAndView;
    }
}
