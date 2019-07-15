[【返回目录】](../README.md)
# 会话共享

## livebos会话共享
>前提：
>1. Livebos系统参数system.session.cluster.enabled设置为true；
>2. web应用同Livebos使用同一个redis作为会话存储，且redis的database必须是同一个，默认0；
>3. 前端采用Nginx将Livebos和web应用映射到通一个IP地址和端口号；

参考配置

```yaml
spring:
  session:
    store-type: redis

  redis:
    host: localhost
    port: 6379
    password: apexsoft
    pool:
      max-active: 100
      max-idle: 5
      max-wait: 60000

aas:
  session:
    sharing: true #true表示开始会话共享，默认false
```

Nginx参考配置

```
server{
        listen 9999;
        server_name  127.0.0.1 localhost;
        # http://127.0.0.1:8090/ 为livebos地址
        # http://127.0.0.1:7070/dd为基于AAS的应用地址
        location /dd {
            proxy_pass http://127.0.0.1:7070/dd;
            proxy_set_header Host $host:$server_port;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header REMOTE-HOST $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        location / {
            proxy_pass http://127.0.0.1:8090/;
            proxy_set_header  X-Real-IP  $remote_addr;
        }
    }

```
[【返回目录】](../README.md)
