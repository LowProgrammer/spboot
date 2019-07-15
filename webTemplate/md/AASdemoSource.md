[【返回目录】](../README.md)
# WebTemplate工程目录结构

```yaml
WebTemplate(工程模版)
├── ams-protocol                // 微服务接口定义文件proto以及生成的代码
│   ├──generatedSrc             // 子模块构建自动生成的源码目录
│   │  └──main
│   │       ├──grpc             // grpc java类（自动构建生成）
│   │       └──java             // protobuf message类（自动构建生成）
│   ├──src
│   │  └──main
│   │       └──proto            // proto定义文件目录
│   └──build.gradle             // 子模块构建文件
├── lib                         // 本地jar包（比如oracle驱动）
├── doc                         // 相关文档
├── webapp-base                 // WEB服务模块（前端，api网关）
│   ├── build.gradle
│   └── src
│       ├──main
│       │    ├── java
│       │    ├── resources
│       │    │   ├── cert                    // 微服务证书目录
│       │    │   ├── application.yml         // 应用配置-总
│       │    │   ├── application-dev.yml     // 应用配置-开发环境
│       │    │   ├── application-pro.yml     // 应用配置-生产环境
│       │    │   ├── bootstrap.yml           // 微服务配置：cert、zk
│       │    │   └── log4j2.yml              // log4j2日志配置
│       │    └── webapp         //webapp目录
│       └── test
├── ams-server-module-demo     //微服务模块
│   ├── build.gradle
│   └── src
│       ├──main
│       │    ├── java
│       │    └── resources
│       │        ├── cert                    // 微服务证书目录
│       │        ├── application.yml         // 应用配置-总
│       │        ├── application-dev.yml     // 应用配置-开发环境
│       │        ├── application-pro.yml     // 应用配置-生产环境
│       │        ├── bootstrap.yml           // 微服务配置：cert、zk
│       │        └── log4j2.yml              // log4j2日志配置
│       └── test
├── ams-server-all               //集成微服务所有模块，合并成一个发布包
├── ams-helloworld                           //helloworld模块
│   ├── build.gradle
│   └── src
│       ├──main
│       │    ├── java
│       │    └── resources
│       │        ├── cert                    // 微服务证书目录
│       │        ├── application.yml         // 应用配置-总
│       │        ├── application-dev.yml     // 应用配置-开发环境
│       │        ├── application-pro.yml     // 应用配置-生产环境
│       │        ├── bootstrap.yml           // 微服务配置：cert、zk
│       │        └── log4j2.yml              // log4j2日志配置
│       └── test
├── build.gradle
├── build-maven.gradle
├── libraries.gradle
├── settings.gradle
└── README.md
```

使用场景
1. ams-helloworld模块demo：开发入门调试模块，用来快速调试开发环境的搭建；
2. ams-protocol模块demo：proto定义模块；
3. ams-server-module-demo模块demo，一般用来开发微服务业务逻辑实现模块；
4. webapp-base模块demo：用来开发web应用的服务端，可以开发微服务，也可以调用微服务；

[【返回目录】](../README.md)
