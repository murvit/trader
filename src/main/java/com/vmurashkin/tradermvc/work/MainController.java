package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.SharesList;
import com.vmurashkin.tradermvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
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
        BigDecimal sum = BigDecimal.ZERO;
        for (Share share : shares) {
            share.getAllData();
            sum = sum.add(share.getBid().multiply(new BigDecimal(share.getQuantity())));
        }
        sum=sum.add(user.getMoney());
        user.setSum(sum);
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("shares", shares);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping("/sign")
    public ModelAndView sign(){
        return new ModelAndView("sign");
    }

    @RequestMapping("/analytic")
    public ModelAndView analytic(){
        List<String> tickers = new SharesList().tickers;
        List<Share>shares = traderDAO.getShareListByTickers(tickers);
        ModelAndView modelAndView = new ModelAndView("analytic");
        modelAndView.addObject("shares", shares);
        return modelAndView;
    }

    @RequestMapping("/buy")
    public ModelAndView buyShares() {
        User user = traderDAO.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("buy");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
