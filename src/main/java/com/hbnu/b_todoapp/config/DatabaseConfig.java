//package com.hbnu.b_todoapp.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import javax.sql.DataSource;
//
//// src/main/java/com/hbnu/b_todoapp/config/DatabaseConfig.java
//@Configuration
//public class DatabaseConfig {
//
//    // 主数据源 (H2)
//    @Primary
//    @Bean(name = "h2DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource h2DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    // MySQL 数据源
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    // H2 的 JPA 配置
//    @Primary
//    @Bean(name = "h2EntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("h2DataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.hbnu.b_todoapp.domain")
//                .persistenceUnit("h2")
//                .properties(Map.of("hibernate.dialect",
//                        "org.hibernate.dialect.H2Dialect"))
//                .build();
//    }
//
//    // MySQL 的 JPA 配置
//    @Bean(name = "mysqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("mysqlDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.hbnu.b_todoapp.domain")
//                .persistenceUnit("mysql")
//                .properties(Map.of("hibernate.dialect",
//                        "org.hibernate.dialect.MySQL8Dialect"))
//                .build();
//    }
//}
//
