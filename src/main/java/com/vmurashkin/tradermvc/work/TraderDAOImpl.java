package com.vmurashkin.tradermvc.work;

import com.vmurashkin.tradermvc.entities.Share;
import com.vmurashkin.tradermvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OG_ML on 07.09.2015.
 */

public class TraderDAOImpl implements TraderDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Share getShareById(int id) {
        return em.find(Share.class, id);
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void addUser(User user) {
        em.merge(user);
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean buyShares(User user, String ticker, int quantity) {

        Share share = new Share(ticker);
        share.setAsk(share.getCurrentAsk());
        share.setQuantity(quantity);
        share.setUser(user);

        BigDecimal newMoney = user.getMoney().subtract(share.getAsk().multiply(new BigDecimal(quantity)));
        if (newMoney.compareTo(new BigDecimal(0)) <= 0 || quantity <= 0) return false;

        boolean isPresent = false;
        for (Share current : user.getShares()) {
            if (current.getTicker().equals(ticker)) {
                user.setMoney(newMoney);
                current.setQuantity(current.getQuantity() + quantity);
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            user.setMoney(newMoney);
            user.addShare(share);
        }
            em.merge(user);
            return true;
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
