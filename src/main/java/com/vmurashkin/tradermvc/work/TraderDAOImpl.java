package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */

public class TraderDAOImpl implements TraderDAO {

    @Autowired
    EntityManager em;

    @Override
    public Share getShareById(int id) {
        Share share = em.find(Share.class, id);
        return share;
    }

    @Override
    public Share getShareByTicker(User user, String ticker) {
        Share share = null;
        String name = user.getName();
        Query query = em.createQuery("SELECT s FROM Share s WHERE s.ticker=:ticker AND s.user_name=:name ", Share.class);
        query.setParameter("ticker", ticker);
        try {
            share = (Share) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return share;
    }

    @Override
    public User getCurrentUser() {
        User user;
        String name;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            name = ((UserDetails) principal).getUsername();
        } else {
            name = principal.toString();
        }
        user = em.find(User.class, name);
        return user;
    }


    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public boolean isUserExist(String name) {
        if (name != null && !"".equals(name)) {
            User user = em.find(User.class, name);
            return (user != null);
        } else return true;
    }

    @Override
    public List<Share> getShareListByUser(User user) {
        return user.getShares();
    }

    @Override
    public List<Share> getWatchShareListByUser(User user) {
        List<Share> shares = new ArrayList<>();
        for (String ticker : user.getTickers()) {
            Share share = new Share(ticker);
            shares.add(share);
        }
        return shares;
    }

    @Override
    public boolean buyShares(User user, String ticker, int quantity) {

        Share share = new Share(ticker);
        share.setAsk(share.getCurrentAsk());
        share.setQuantity(quantity);
        BigDecimal newMoney = user.getMoney().subtract(share.getAsk().multiply(new BigDecimal(quantity)));
        if (newMoney.compareTo(new BigDecimal(0)) <= 0 || quantity<=0) return false;

        boolean isPresent = false;
        for (Share current : user.getShares()) {
            if (current.getTicker().equals(ticker)) {
                user.setMoney(newMoney);
                current.setQuantity(current.getQuantity()+quantity);
                isPresent =  true;
                break;
            }
        }
        if (!isPresent) {
            user.setMoney(newMoney);
            user.addShare(share);

        }
//        Query query = em.createQuery("SELECT s FROM Share s WHERE s.user=:user AND s.ticker = :ticker", Share.class);
//        query.setParameter("user", user);
//        query.setParameter("ticker", ticker);
//
//        try {
//            share = (Share) query.getSingleResult();
//        } catch (NoResultException | NonUniqueResultException e) {
//            e.printStackTrace();
//        }
//        if (share == null)
//            share = new Share();
        try {
            em.getTransaction().begin();
//            share.setTicker(ticker);
//            share.setQuantity(share.getQuantity() + quantity);
//            share.setUser(user);
//            user.addShare(share);
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
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
