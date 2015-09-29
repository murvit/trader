package com.vmurashkin.tradermvc.work;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by OG_ML on 07.09.2015.
 */

@Configuration
@ComponentScan("com.vmurashkin.tradermvc")
@EnableWebMvc
public class AppConfig {

    private static EntityManagerFactory EMFinstance;

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean//(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/trader");
        driverManagerDataSource.setUsername("testuser");
        driverManagerDataSource.setPassword("testpassword");
        return driverManagerDataSource;
    }

    @Bean
    public TraderDAO traderDAO() {
        return new TraderDAOImpl();
    }

    @Bean
    public static EntityManager getEMFinstance() {
        if (EMFinstance == null)
            EMFinstance = Persistence.createEntityManagerFactory("trader");
        return EMFinstance.createEntityManager();
    }


}
