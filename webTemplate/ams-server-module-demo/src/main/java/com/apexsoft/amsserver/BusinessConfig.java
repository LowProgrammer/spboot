package com.apexsoft.amsserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 下午3:38 2018/4/9
 * @MODIFIED BY:
 */
@ConfigurationProperties("demo")
@Component
public class BusinessConfig {

    private String client;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
