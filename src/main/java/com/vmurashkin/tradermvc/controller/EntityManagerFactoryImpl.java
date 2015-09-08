package com.vmurashkin.tradermvc.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by OG_ML on 25.08.2015.
 */

public class EntityManagerFactoryImpl {
    private static EntityManagerFactory instance;

    private EntityManagerFactoryImpl() {
    }

    public static EntityManagerFactory getInstance() {
        if (instance == null)
            instance = Persistence.createEntityManagerFactory("trader");
        return instance;
    }
}
