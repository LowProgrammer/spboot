# 埋点插件使用说明
### yml配置说明
**配置参数**
```yaml
aas:
  count:
    enabled: true
    kafka: true
    #启用ES字典数据同步本地表,默认为false，在行为分析控制台需要开启
    sync-data: true
spring:
  kafka:
   bootstrap-servers: 192.168.3.137:9092  
#可配置多个逗号隔开(192.168.3.137:9092,192.168.3.138:9092)
#如果配置中心没有配置es属性则需要配置以下参数
ams: 
  metrics:
    es:
      hosts: 192.168.3.133:9200
      username: elastic
      password: {cipher}EAjhRq1OD9p3yNftNHSotWyUdUop5Q/TGalvkJLRSPrX
```
### build.gradle 配置说明
- 在build.gradle添加jar包
    - compile ('org.elasticsearch:elasticsearch:6.2.3')

### 埋点前端调用说明

[Web调用](AAS-COUNT-Web.md)

[React调用](AAS-COUNT-React.md)

[ReactNative调用](AAS-COUNT-ReactNative.md)

[行为分析数据报文结构](AAS-COUNT-Data.md)

### 其他协助服务
[埋点消息监听服务说明](AAS-COUNT-CONSUMER.md)

[埋点消息监听服务](https://git.apexsoft.com.cn/framework/server/aas-count-consumer)

[kafka服务](http://kafka.apache.org/)

[elasticsearch服务](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)


