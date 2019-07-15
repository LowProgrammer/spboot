package com.feifei.spboot.learn.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Classname JpaConfiguration
 * @Description TODO
 * @Date 2019/7/12 13:56
 * @Created by ChenS
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration//
@EnableTransactionManagement(proxyTargetClass = true)//jpa事务管理
@EnableJpaRepositories(basePackages = "com.feifei.spboot.learn.repository")//jpa资源库
@EntityScan(basePackages = "com.feifei.spboot.learn.Entity")//定义实体位置
public class JpaConfiguration {

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }


}
