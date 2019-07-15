package com.apexsoft.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@Configuration
@MapperScan(basePackages = "com.apexsoft.c5", sqlSessionTemplateRef  = "mainSqlSessionTemplate")
public class MainDsConfig extends DruidDataSource{

    @Primary
    @Bean(name="mainDs")
    //@RefreshScope
    @ConfigurationProperties(prefix="spring.datasource.mainDs")
    public DataSource dataSource() {
        return this;
    }

    @Primary
    @Bean(name = "mainSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mainDs")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/main/*.xml"));

        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(ibatisConfiguration);

        return bean.getObject();
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("mainDs") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "mainSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
