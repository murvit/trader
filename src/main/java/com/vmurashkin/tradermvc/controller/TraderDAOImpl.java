package com.vmurashkin.tradermvc.controller;

import com.vmurashkin.tradermvc.model.Share;
import com.vmurashkin.tradermvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */

public class TraderDAOImpl implements TraderDAO {

 //   EntityManagerFactory emf = EntityManagerFactoryImpl.getInstance();
    @Autowired
    EntityManager em;// = emf.createEntityManager();

    @Override
    public User getUser(int id) {
        User user = em.find(User.class, 1);
        List<Share> shares = user.getShares();
        return user;
    }

    @Override
    public List<Share> getShareList(User user) {
        return user.getShares();
    }

    @Override
    public void buyShares(User user, String ticker, int quantity) {
        Query query = em.createQuery("SELECT s FROM Share s WHERE s.user=:user AND s.ticker = :ticker", Share.class);
        query.setParameter("user", user);
        query.setParameter("ticker", ticker);
        Share share = null;
        try {
            share = (Share) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        if (share == null)
            share = new Share();
        try {
            em.getTransaction().begin();
            share.setTicker(ticker);
            share.setQuantity(share.getQuantity() + quantity);
            share.setUser(user);
            em.merge(share);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void sellShares(User user, String ticker, int quantity) {
    }

    @Override
    public void closeAll() {
        em.close();
     //   emf.close();
    }

    public TraderDAOImpl() {
    }

}
