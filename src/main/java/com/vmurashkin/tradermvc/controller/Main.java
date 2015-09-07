package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.Share;
import com.vmurashkin.tradermvc.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


/**
 * Created by OG_ML on 18.08.2015.
 */


public class Main {

    public static void main(String[] args) {


        GetShareData getShareData = new GetShareDataImpl();
        ParsingJSON parsingJSON = new ParsingJSONImpl();

//        String ticker = "AAPL";
//        String answer = getShareData.getData(ticker);
//        Share share = parsingJSON.parse(answer);
//        System.out.println(share);


        ShareManipulator manipulator = new ShareManipulatorImpl();
        User user = manipulator.getUser(1);
        List<Share> shares = user.getShares();
        for (Share share : shares) {
            share.getAllData();
            System.out.println(share);
            System.out.println("Current Bid: " + share.getCurrentBid());
            System.out.println("Current Ask: " + share.getCurrentAsk());
        }

        String ticker = "AAPL";
        int quantity = 500;
        manipulator.buyShares(user, ticker, quantity);
//        ticker = "NEW";
//        quantity = 300;
//        manipulator.buyShares(user, ticker, quantity);
        manipulator.closeAll();

    }
}
