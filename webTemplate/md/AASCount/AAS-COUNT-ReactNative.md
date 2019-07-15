react-native-apex-platform-event-log
====================================

**概述**

[react-native-apex-platform-event-log](https://git.apexsoft.com.cn/framework/mobile/react-native-apex-platform-event-log)<br/>

通过`npm i react-native-apex-platform-event-log --save`安装<br/>

```
本组件是纯js实现事件与页面埋点api，需要依赖公共根组件
[react-native-apex-platform-common](https://git.apexsoft.com.cn/framework/mobile/react-native-apex-platform-common.git)
```

**API说明**

**EventLogConfigType类型**

```javascript
/**
 * SDK配置数据类型
 */
export type EventLogConfigType = {
    clientId:string,//唯一标识符
    clientSecret:string,//加密串
    channel:string,//渠道
    domainKey:string,//日志上报地址key
    reportInterval:number,//上报间隔时间（单位：分钟）
    reactVersion:string//js版本号

};

```

**EventBodyType类型**

```javascript
/**
 * 事件数据模型
 */
export type EventBodyType = {
    event:string,//事件标识
    action:string,//细化行为
    ext:string,//额外信息
    uid:?string,//访客标识,API生成
    datetime:?number,//时间戳,API生成
    userId:?string,//用户ID:业务的ID
    label:array//标签数组：预留，为用户行为分析的自定义分组
};

```

**PageBodyType类型**

```javascript
/**
 * 页面数据模型
 */
export type PageBodyType = {
    clientId:?string,
    url:string,//访问组件
    ref_url:string,//上级组件
    uid:?string,//访客标识,API生成
    datetime:?number,//时间戳,API生成
    userId:?string,//用户ID:业务的ID
    label:?array//标签数组：预留，为用户行为分析的自定义分组
};


```

**setupApexLogEvent = (config:EventLogConfigType) =>{}**

```
用途：设置埋点参数配置
参数：config：配置参数(参见：EventLogConfigType类型)
返回值：无
```

**getApexEventLogVersion = async () =>{}**

```
用途：获取当前埋点事件版本号
参数：无
返回值：版本号
```

**destoryApexLogEvent = () =>{}**

```
用途：卸载日志组件
参数：无
返回值：无
```

**logEvent = async (eventBody:EventBodyType = {}) =>{}**

```
用途：记录特定事件
参数：eventBody：事件参数(参见：EventBodyType类型)
返回值：无
```

**logEventStart = async (eventBody:EventBodyType = {}) =>{}**

```
用途：记录特定事件开始
参数：eventBody：事件参数(参见：EventBodyType类型)
返回值：无
```

**logEventEnd = async (eventBody:EventBodyType = {}) =>{}**

```
用途：记录特定事件结束
参数：eventBody：事件参数(参见：EventBodyType类型)
返回值：无
```

**pageEventStart = async (pageBody:PageBodyType = {}) =>{}**

```
用途：记录特定页面事件开始
参数：pageBody：事件参数(参见：PageBodyType类型)
返回值：无
```


**pageEventEnd = async (pageBody:PageBodyType = {}) =>{}**

```
用途：记录特定页面事件结束
参数：pageBody：事件参数(参见：PageBodyType类型)
返回值：无
```
