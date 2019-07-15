package com.apexsoft.webapp.modules.mysql.model;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
public class Demo {

    private int id;
    private String appName;
    private String appCode;
    private String appDesc;
    private int appStatus;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppCode(String appCode) { this.appCode = appCode; }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }


}
