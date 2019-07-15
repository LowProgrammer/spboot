package com.apexsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午9:51 2018/3/1
 * @MODIFIED BY:
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AmsServerApplication{

    public static void main(String[] args) {
        SpringApplication.run(AmsServerApplication.class, args);
    }

}
