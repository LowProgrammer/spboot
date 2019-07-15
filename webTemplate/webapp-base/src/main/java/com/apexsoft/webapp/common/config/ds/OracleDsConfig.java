package com.apexsoft.webapp.common.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@MapperScan(basePackages = "com.apexsoft.webapp.modules.oracle", sqlSessionTemplateRef  = "oracleDsSqlSessionTemplate")
public class OracleDsConfig extends DruidDataSource{

    @Bean(name="oracleDs")
    @ConfigurationProperties(prefix="spring.datasource.oracleDs")
    public DataSource dataSource() {
        return this;
    }

    @Bean(name = "oracleDsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oracleDs")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/oracle/*.xml"));

        //mybatis设置驼峰字段映射
        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(ibatisConfiguration);

        return bean.getObject();
    }

    @Bean(name = "oracleDsTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("oracleDs") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oracleDsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("oracleDsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
