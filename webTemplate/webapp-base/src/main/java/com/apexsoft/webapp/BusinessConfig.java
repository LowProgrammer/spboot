package com.apexsoft.webapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@ConfigurationProperties("abc")
@Component
//@RefreshScope
public class BusinessConfig {

    //@Value("${project-name:wt}")
    private String projectName;

    //@Value("${node-name:default-node}")
    private String nodeName;

    //@Value("${version:0.0.1}")
    private String version;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
