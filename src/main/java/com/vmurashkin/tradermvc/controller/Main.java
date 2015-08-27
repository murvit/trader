package com.vmurashkin.tradermvc.controller;



import com.vmurashkin.tradermvc.model.Share;
import com.vmurashkin.tradermvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by OG_ML on 18.08.2015.
 */
public class Main {

    public static void main(String[] args)  {
//        GetShareData getShareData = new GetShareDataImpl();
//        ParsingJSON parsingJSON = new ParsingJSONImpl();
//        String ticker = "AAPL";
//        String answer = getShareData.getData(ticker);
//        Share share = parsingJSON.parse(answer);
//        System.out.println(share);


        EntityManagerFactory emf = EntityManagerFactoryImpl.getInstance();
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, 1);
        List<Share> shares = user.getShares();
        for (Share share : shares){
            System.out.println(share.getTicker() + ", " + share.getQuantity());
        }


//        Query query = em.createQuery("SELECT share_id, quantity FROM trader.portfolio where user_id = 1")
em.close();
        emf.close();
    }
}
