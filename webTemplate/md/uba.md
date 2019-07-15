[【返回目录】](../README.md)
# 用户行为分析(A.UBA)

## 概述

用户行为分析是一个独立的应用服务，主要负责接收浏览器，APP的行为数据，同时将数据落地到elasticsearch存储和提供分析结果数据接口。


## 部署
1. 前置条件
     * 部署微服务环境：关键需要ZK服务
     * 部署配置中心：主要提供elasticsearch的地址配置
     * 部署蜂巢：用户行为分析的可视化界面
     * JDK8：服务的运行环境要求
     * MYSQL环境：要求mysql5.7+

2. 数据库初始化

    执行aas-common.sql,aas-uba.sql两个初始化脚本

3. 配置文件修改

    如下配置文件可以从服务jar包中BOOT-INF/classes/下找到

    application-prod.yml

    ```yml
        application:
          #应用名称，必须配置，集群中的应用名称必须一致(修改点)
          name: aas.count
        server:
          #服务端口(修改点)
          port: 8080
        spring:
          datasource:
                #行为分析数据源，固定为aasDs，不可更改(修改点)
               aasDs:
                 name: aas-mysql
                 driver-class-name: com.mysql.cj.jdbc.Driver
                 url: jdbc:mysql://192.168.3.28:3306/dd?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8
                 username: dongdong
                 password: dongdong
                 initialSize: 5
                 maxActive: 20
                 validationQuery: select 1
                 testWhileIdle: true
                 testOnBorrow: false
                 testOnReturn: false
                 poolPreparedStatements: true
                 maxOpenPreparedStatements: 100
                 filters: stat,wall
        aas:
          count:
            enabled: true
          amc:
            enabled: true
          swagger:
            enabled: true
    ```

    bootstrap.yml

    ```yml
        ams:
          authority: livebos-server
          ca:
            certFile: classpath:cert/ca.pem
          client:
            certFile: classpath:cert/client.pem
            keyFile: classpath:cert/client.pkcs8.pem
          server:
            certFile: classpath:cert/server.pem
            keyFile: classpath:cert/server.pkcs8.pem
          registry:
            inner: false
            protocol: zk
            #address: 192.168.4.171:2181,192.168.4.172:2181,192.168.4.173:2181
            #address: 192.168.3.131:2181,192.168.3.132:2181,192.168.3.133:2181
            #username: amscli
            #password: apexsoft
            # ZK微服务的地址(修改点)
            address: 192.168.3.24:2181
            #address: 192.168.0.87:2181,192.168.0.98:2181,192.168.0.99:2181
    ```


    log4j2.yml

    ```yml
    Configuration:
      status: info

      Properties: # 定义全局变量
        Property: # 缺省配置（用于开发环境）。其他环境需要在VM参数中指定，如下：
          - name: log.level
            value: info

      Appenders:
        Console:  #输出到控制台
          name: CONSOLE
          target: SYSTEM_OUT
          PatternLayout:
            pattern: "%d{yyyy-MM-dd HH:mm:ss,SSS}:%4p %t (%F:%L) - %m%n"
        RollingFile: # 输出到文件，超过128MB归档
          - name: ROLLING_FILE
            ignoreExceptions: false
            fileName: logs/main.log
            filePattern: "logs/main-%d{yyyy-MM-dd-HHmmss}.gz"
            PatternLayout:
              pattern: "%d{yyyy-MM-dd HH:mm:ss,SSS}:%4p %t (%F:%L) - %m%n"
            Policies:
              SizeBasedTriggeringPolicy:
                size: "100MB"

      Loggers:
        Root:
          level: ${log.level}
          AppenderRef:
            - ref: CONSOLE
            - ref: ROLLING_FILE

    ```

4. 服务启动

    如果不指定log4j2.yml,会直接使用jar中的配置文件

    > nohup java -Dlog4j.configurationFile=log4j2.yml -jar aas-count-server-1.0.0-SNAPSHOT.jar &

## 前端接入说明

[Web调用](AASCount/AAS-COUNT-Web.md)

[React调用](AASCount/AAS-COUNT-React.md)

[ReactNative调用](AASCount/AAS-COUNT-ReactNative.md)

[行为分析数据报文结构](AASCount/AAS-COUNT-Data.md)

## 其他协助服务

[elasticsearch服务](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)

[分析数据展示](AASCount/OperationManual.md)

[【返回目录】](../README.md)