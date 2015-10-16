package com.vmurashkin.tradermvc.work;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Application configuration
 */

@Configuration
@ComponentScan("com.vmurashkin.tradermvc")
@EnableWebMvc
@EnableTransactionManagement
@Import({AppSecurityConfig.class})
public class AppConfig {

//    private static EntityManagerFactory EMFinstance;

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

//        driverManagerDataSource.setUrl("jdbc:mysql://mysql22751-trader.unicloud.pl:3306/trader?characterEncoding=UTF-8");
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("IOSfmx55982");


        driverManagerDataSource.setUrl("jdbc:mysql://localhost/trader?characterEncoding=UTF-8");
        driverManagerDataSource.setUsername("testuser");
        driverManagerDataSource.setPassword("testpassword");

        return driverManagerDataSource;
    }

    @Bean
    public TraderDAO traderDAO() {
        return new TraderDAOImpl();
    }

//    @Bean
//    public static EntityManager getEMinstance() {
//        if (EMFinstance == null)
//            EMFinstance = Persistence.createEntityManagerFactory("trader");
//        return EMFinstance.createEntityManager();
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.vmurashkin.tradermvc" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }

}
