package com.apexsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:21 2018/3/30
 * @MODIFIED BY:
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.apexsoft")
public class AmsAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmsAllApplication.class, args);
    }

}
