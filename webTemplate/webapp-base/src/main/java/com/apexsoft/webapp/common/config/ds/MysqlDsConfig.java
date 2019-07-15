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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 下午5:16 2018/4/4
 * @MODIFIED BY:
 */
@Configuration
@MapperScan(basePackages = "com.apexsoft.webapp.modules.mysql", sqlSessionTemplateRef  = "mysqlDsSqlSessionTemplate")
public class MysqlDsConfig extends DruidDataSource {

    @Primary
    @Bean(name="mysqlDs")
    @ConfigurationProperties(prefix="spring.datasource.mysqlDs")
    public DataSource dataSource() {
        return this;
    }

    @Bean(name = "mysqlDsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDs")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mysql/*.xml"));

        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(ibatisConfiguration);

        return bean.getObject();
    }

    @Bean(name = "mysqlDsTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("mysqlDs") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlDsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlDsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
