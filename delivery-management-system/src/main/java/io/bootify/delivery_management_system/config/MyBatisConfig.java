//package io.bootify.delivery_management_system.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
//@Configuration
//public class MyBatisConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:io/bootify/delivery_management_system/mapper/*.java"));
//        return factory.getObject();
//    }
//}