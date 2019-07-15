# 基于WebTemplate的开发手册 #
## WebTemplate是什么？##

> `webTemplate`是采用`gradle`构建，基于`SpringBoot`开发框架的项目工程模版，其中包含了`web项目``的开发模块和基于`AMS微服务``的开发模块。应用项目可以基于此工程为原型，快速搭建标准的服务端开发环境;

## AAS是什么? ##

> `AAS`（全称Apex Application Support）是一个`SpringBoot`的`starter`组件，`aas-springboot-starter`集成了顶点基础模API的支持，包含：监控API组件、服务端公共组件、统一鉴权组件、移动端基础支持模块、行为埋点采集模块、消息推送引擎、DB-GATE模块的支持；webTemplate使用该插件，项目将无缝集成了这些底层的支持，为后续的统一运维、管理、监控提供统一支持；

[AAS更新日志](AAS_UPDATE.md)

## “蜂巢”是什么？ ##

> `蜂巢`是应用架构研发部开发的统一应用管理、监控平台：包含了应用管理监控、应用可视化配置、微服务监控、服务治理、行为分析、APM，系统管理等可视化的支持；


## 文档目录

- [系统部署框架图](md/framework.md)
- [开发协作模式](md/devModel.md)
- [开源技术和功能特性](md/AASFeature.md)
- 从这里开始
    - [开发环境的搭建](md/devEnvironment.md)
    - [WebTemplate工程目录结构](md/AASdemoSource.md)
    - [第一个微服务](md/firstAms.md)
    - [第一个web应用](md/firstWebapp.md)
- 基于WebTemplate开发指导
    - [application配置说明](md/AASappConfig.md)
    - [restFul接口开发](md/AASrestful.md)
    - AAS插件
        -  [Swagger插件](md/AASswagger.md)
        -  [数据格式校验](md/AASvalidate.md)
        -  [数据加密](md/AASGuard.md)
        -  [Session控制](md/AASsession.md)
        -  [LiveBos和Simple会话模式](md/AASauthentication.md)
        -  [会话共享](md/AASsessionSharing.md)
        -  [行为分析插件](md/AASCount/PLUGIN.md)
        -  [DB-GATE插件](md/AASdbgate.md)
        -  [消息通信插件（未发布）](md/README.md)
        -  [咚咚服务插件](md/AASDong.md)
    - 微服务开发指导
        - [微服务定义](md/AMSdef.md)
        - [微服务开发](md/AMSdep.md)
- 蜂巢
    - [平台概述](md/README.md)
    - 操作手册
        -  [应用管理](md/appManager.md)
            - [应用概况](md/appGeneral.md)
            - [应用终端](md/appClient.md)
            - [用户会话管理](md/userSession.md)
            - [APP](md/App.md)
            - [行为分析](md/AASCount/OperationManual.md)
            - [DB-GATE](md/db-gate.md)
        -  服务治理
            -  [DashBoard](md/Dashboard.md)
            -  [服务](md/service.md)
            -  [参与者](md/serviceActor.md)
            -  [配置中心](md/amsConfig.md)
        -  [行为分析](md/AASCount/OperationManual.md)
        -  [APM](md/AMP.md)
        -  系统管理
            -  [应用接入用户](md/appAccess.md)
            -  [操作人员](md/mgrUser.md)
            -  [操作日志](md/log.md)
            -  [本机配置](md/localInfo.md)
            -  [设置](md/setting.md)
- [统一认证](md/AcCenter.md)
    - [概述](md/AcCenter.md#概述)
    - [系统框架](md/AcCenter.md#系统框架)
    - [部署](md/AcCenter.md#部署)
    - [第三方集成](md/AcCenter.md#第三方集成)
- [用户行为分析](md/uba.md)
    - [概述](md/uba.md#概述)
    - [部署](md/uba.md#部署)
    - [前端接入说明](md/uba.md#前端接入说明)
    - [其他协助服务](md/uba.md#其他协助服务)