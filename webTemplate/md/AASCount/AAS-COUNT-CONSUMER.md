# 埋点消息采集服务说明:
### 服务部署文件内容
* war包：consumer-0.0.1-SNAPSHOT.war
* IP库：ip2region.db
* config文件夹，文件夹目前只包含application.yml

### War包、IP库文件、config文件部署在同一个文件内

### application.yml配置
```yaml
server:
  port: 8989
  context-path: /

#kafka相关配置
spring:
  kafka:
    #指定kafka 代理地址，可以多个,逗号隔开 192.168.3.137:9092,192.168.3.138:9092
    bootstrap-servers: 192.168.3.137:9092
    consumer:
      group-id: aas-count
      enable-auto-commit: false
    # 指定listener 容器中的线程数，用于提高并发量,与 Kafka分区一样
    listener:
      concurrency: 1
      ack-mode: manual_immediate

#elasticsearch 服务地址端口配置
es:
  scheme: http
  #host: es服务地址可以多个,逗号隔开 192.168.80.187,192.168.3.133
  host: 192.168.3.133
  port: 9200
```
### application.yml自行修改项
* server.port
* spring.kafka.bootstrap-servers
* es.host



