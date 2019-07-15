[【返回目录】](../README.md)

# ams-protocol 微服务接口定义模块

> 此模块下除了`*.proto`文件，其他java代码都是`gradle插件`自动生成

# 1、proto样例

## 公共消息对象定义
```proto
syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.apex.ams.common";

//请求消息
message CommonRequest {
    string sessionId = 1;
    string serviceId = 2;
    string header = 3;
    string body =4;
}
//响应消息
message CommonResponse {
    int32 code = 1;
    string note = 2;
    repeated CommonRecord records = 3;
    int32 count = 4;
}
//结果集对象消息
message CommonRecord{
    string row = 1;
    int32 id = 2;
    string appName = 3;
    string appCode = 4;
    string appDesc = 5;
    int32 appStatus = 6;
}
//流式结果
message StreamResponse {
    int32 code = 1;
    string note = 2;
    int32 count = 3;

    CommonRecord record = 4;
}
```

## 服务以及方法定义
```proto
syntax = "proto3";

import "common/common.proto";
package esb.demo;

option java_multiple_files = true;
option java_package = "com.apex.ams.esb";


//服务方法
service EsbService {

    //直接使用repeated消息方式返回。
    //  优点： 简单清晰，更符合一般开发习惯
    //  缺点： 大数据集返回需要耗费更多系统资源与响应时间
    rpc service (CommonRequest) returns (CommonResponse) {}


    //使用stream流方式返回
    //  优点： 大数据集返回效率更好
    //  缺点： 消息定义语义有点不清晰
    rpc streamService (CommonRequest) returns (stream StreamResponse) {}
}

```


# 2、消息值类型
|.proto Type|Java Type|默认值|说明|
|------|------|:------:|------|
|string|String|空的string| |
|bool|boolean|false| |
|double|double|0| |
|float|float|0| |
|int32|int|0|使用可变长度编码。对负数进行编码时比较低效 – 如果你的字段要使用负数值，请使用sint32来代替。|
|int64|long|0|使用可变长度编码。对负数进行编码时比较低效 – 如果你的字段要使用负数值，请使用sint64来代替。|
|uint32|int[1]|0|使用可变长度编码。无符号|
|uint64|long[1]|0|使用可变长度编码。无符号|
|sint32|int|0|使用可变长度编码。|
|sint64|long|0|使用可变长度编码。|
|bytes|ByteString|空的bytes| |

[【返回目录】](../README.md)
