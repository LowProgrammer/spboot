package com.apexsoft.webapp.common.config.ds;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@Configuration
public class ImpalaDsConfig {

    @Bean(name="impalaDataSource")
    //@RefreshScope
    @ConfigurationProperties(prefix="spring.datasource.impala")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "impalaJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
