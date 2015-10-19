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
import java.math.MathContext;
import java.util.List;

/**
 * Main controller
 */

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private TraderDAO traderDAO;// = new TraderDAOImpl();

    @RequestMapping({"/", "/hello"})
    @Transactional
    public ModelAndView listShares() {
        User user = traderDAO.getCurrentUser();
        user.countSum();
        ModelAndView modelAndView = new ModelAndView("hello");
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
//            System.out.println(share.getTicker());
        }

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
        int quantity = user.getMoney().divide(new Share(ticker).getCurrentAsk(),BigDecimal.ROUND_DOWN).intValue();
        modelAndView.addObject("quantity", quantity);
        return modelAndView;
    }

    @RequestMapping("/buyshare")
    @Transactional
    public ModelAndView buyShare(@RequestParam(value = "quantity") int quantity,
                                 @RequestParam(value = "ticker") String ticker,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        User user = traderDAO.getCurrentUser();
        boolean success = traderDAO.buyShares(user, ticker, quantity);
        if (success) {
            return listShares();

        } else {
            ModelAndView modelAndView = new ModelAndView("buy");
            modelAndView.addObject("user", user);
            modelAndView.addObject("ticker", ticker);
            modelAndView.addObject("error", "error");
            modelAndView.addObject("quantity",
                    user.getMoney().divide(new Share(ticker).getCurrentAsk(),BigDecimal.ROUND_DOWN).intValue());
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
        return modelAndView;
    }

    @RequestMapping("/sellshare")
    @Transactional
    public ModelAndView sellShare(@RequestParam(value = "quantity") int quantity,
                                  @RequestParam(value = "ticker") String ticker,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
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


}
