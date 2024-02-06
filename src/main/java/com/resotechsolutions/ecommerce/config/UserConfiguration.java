package com.resotechsolutions.ecommerce.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UserConfiguration {

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

//    private Properties jpaProperties() {
//        Properties properties = new Properties();
//        // properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        return properties;
//    }

    @Primary
    @Bean(name = "mysqlDb")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mysqlDataSource() {
        System.out.println("****** connection created successfully *******");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mysqljpa")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
            @Qualifier("mysqlDb") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
        entityManagerFactoryBean.setPersistenceUnitName("MYSQL_DB");
        entityManagerFactoryBean.setPackagesToScan("com.resotechsolutions.ecommerce");
        entityManagerFactoryBean.setJpaProperties(mySqlJpaProperties());

        return entityManagerFactoryBean;
    }

    private Properties mySqlJpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        // properties.put("hibernate.dialect",
        // "org.hibernate.dialect.Oracle10gDialect");
        return properties;
    }

    @Primary
    @Bean(name = "defaultTransactionManager")
    public PlatformTransactionManager defaultTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                mySqlEntityManagerFactory(null).getObject());
        return transactionManager;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        return http.build();
    }

}


//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.Properties;
//
//@Configuration
//public class UserConfiguration {
//
//
//    @Bean
//    public JpaVendorAdapter getJpaVendorAdapter() {
//        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        return adapter;
//    }
//    private Properties jpaProperties() {
//        Properties properties = new Properties();
////        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
////        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb");
////        properties.put("hibernate.connection.username", "root");
////        properties.put("hibernate.connection.password", "root");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }
//
//    @Primary
//    @Bean(name = "mysqlDb")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource mysqlDataSource() throws SQLException {
//        System.out.println("****** connection created successfully *******");
//        DataSource dataSource = DataSourceBuilder.create().build();
//        System.out.println(dataSource.getLogWriter());
//        return dataSource;
//    }
//
//    @Primary
//    @Bean(name = "mysqljpa")
//    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
//            @Qualifier("mysqlDb") DataSource dataSource) {
//
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
//        entityManagerFactoryBean.setPersistenceUnitName("testdb"); //name of database schema
//        entityManagerFactoryBean.setPackagesToScan("com.resotechsolutions.ecommerce.entity");
//        entityManagerFactoryBean.setJpaProperties(jpaProperties());
//
//        return entityManagerFactoryBean;
//    }
//
//
//
//    @Primary
//    @Bean(name = "defaultTransactionManager")
//    public PlatformTransactionManager defaultTransactionManager() throws SQLException {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                mySqlEntityManagerFactory(null).getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(12);
//    }
//
//}
