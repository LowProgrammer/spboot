[【返回目录】](../README.md)
## DB-GATE
1. 数据库准备

    准备可访问的mysql数据库，执行如下脚本
    [aas-dbgate.sql](../scripts/aas-dbgate.sql "目录")

2. 配置数据源

    需要应用添加aasDs数据源，地址为步骤1准备好的数据库

    ```yaml

    spring:
     datasource:
        aasDs:
          name: aas-mysql
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://192.168.3.28:3306/dd?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&nullNamePatternMatchesAll=true&noAccessToProcedureBodies=true
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

    ```


3. 启用db-gate配置

    ```yaml

    aas:
      dbgate:
          enabled: true  # true:开启 false:关闭

    ```
[【返回目录】](../README.md)
