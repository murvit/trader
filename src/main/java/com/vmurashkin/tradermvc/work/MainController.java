package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * Main controller
 */

@Controller
// @RequestMapping("/")
public class MainController {
    @Autowired
    private TraderDAO traderDAO;

    @RequestMapping({"/", "/hello"})
    @Transactional
    public ModelAndView listShares() {
        User user = traderDAO.getCurrentUser();
        List<Share> shares = user.getShares();
        traderDAO.setData(shares);
        traderDAO.countSum(user, shares);
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("user", user);
        modelAndView.addObject("shares", shares);
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
        traderDAO.setData(shares);
        ModelAndView modelAndView = new ModelAndView("analytic");
        modelAndView.addObject("shares", shares);
        return modelAndView;
    }

    @RequestMapping("/buy")
    public ModelAndView buyShares(@RequestParam(value = "ticker") String ticker) {
        User user = traderDAO.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("buy");
        modelAndView.addObject("user", user);
        modelAndView.addObject("ticker", ticker);
        Share share = new Share(ticker);
        traderDAO.setShareData(share);
        int quantity = user.getMoney().divide(share.getAsk(), BigDecimal.ROUND_DOWN).intValue();
        modelAndView.addObject("quantity", quantity);
        modelAndView.addObject("share", share);
        return modelAndView;
    }

    @RequestMapping("/buyshare")
    @Transactional
    public ModelAndView buyShare(@RequestParam(value = "quantity") int quantity,
                                 @RequestParam(value = "ticker") String ticker) {
        User user = traderDAO.getCurrentUser();
        boolean success = traderDAO.buyShares(user, ticker, quantity);
        if (success) {
            return listShares();

        } else {
            ModelAndView modelAndView = new ModelAndView("buy");
            modelAndView.addObject("user", user);
            modelAndView.addObject("ticker", ticker);
            modelAndView.addObject("error", "error");
            Share share = new Share(ticker);
            traderDAO.setShareData(share);
            modelAndView.addObject("quantity",
                    user.getMoney().divide(share.getAsk(), BigDecimal.ROUND_DOWN).intValue());
            return modelAndView;
        }
    }

    @RequestMapping("/sell")
    public ModelAndView sellShares(@RequestParam(value = "ticker") String ticker) {
        User user = traderDAO.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("sell");
        modelAndView.addObject("user", user);
        modelAndView.addObject("ticker", ticker);
        modelAndView.addObject("quantity", traderDAO.getShareByTicker(user, ticker).getQuantity());
        Share share = new Share(ticker);
        traderDAO.setShareData(share);
        modelAndView.addObject("share", share);
        return modelAndView;
    }

    @RequestMapping("/sellshare")
    @Transactional
    public ModelAndView sellShare(@RequestParam(value = "quantity") int quantity,
                                  @RequestParam(value = "ticker") String ticker) {
        User user = traderDAO.getCurrentUser();
        boolean success = traderDAO.sellShares(user, ticker, quantity);
        if (success) {
            return listShares();

        } else {
            ModelAndView modelAndView = new ModelAndView("sell");
            modelAndView.addObject("user", user);
            modelAndView.addObject("ticker", ticker);
            modelAndView.addObject("error", "error");
            modelAndView.addObject("quantity", traderDAO.getShareByTicker(user, ticker).getQuantity());
            return modelAndView;
        }
    }

    @RequestMapping("/remove")
    @Transactional
    public ModelAndView removeTicker(@RequestParam(value = "ticker") String ticker) {
        User user = traderDAO.getCurrentUser();
        traderDAO.removeTicker(user, ticker);
        return analytic();
    }

    @RequestMapping("/restore")
    @Transactional
    public ModelAndView restoreTickers() {
        User user = traderDAO.getCurrentUser();
        traderDAO.restoreTickers(user);
        return analytic();
    }


}
