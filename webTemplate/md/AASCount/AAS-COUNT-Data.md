# 行为分析数据报文结构 #

页面分析数据

```json
       {
         "country": "中国",     /*所在国家*/
         "appVersion": "1.1",     /*应用版本*/
         "clientId": "amc-server",     /*终端ID*/
         "os": "Linux",     /*操作系统*/
         "city": "郑州市",     /*所在城市*/
         "mdVersion": "1.0.0",     /*行为分析插件版本*/
         "channel": "腾讯应用商店",     /*渠道*/
         "ref_url": "/test/page2",     /*上级页面地址*/
         "datetimeLong": 1532920571030,     /*请求时间戳*/
         "xRealIp": "223.211.192.124",     /*请求IP*/
         "resolution": "4019x2019",     /*分辨率*/
         "url": "/test/page3",     /*请求地址*/
         "facilitator": "鹏博士",     /*网络服务商*/
         "uid": "2de5f26f-7c0b-4261-98c4-30cb944342c8",     /*会话ID*/
         "datetime": "2018-07-30T03:16:11.030Z",     /*请求时间戳*/
         "osVersion": "7.0",     /*操作系统版本*/
         "stayTime": 12222,     /*停留时间*/
         "browser": "Chrome",     /*浏览器类型*/
         "browserVersion": "11",     /*浏览器版本*/
         "imei": "0000000-00",     /*设备ID*/
         "model": "X1",     /*设备型号*/
         "region": "河南",     /*所在省份*/
         "brand": "oppo",     /*品牌*/
         "label":{
             "label1" : "xxxxx",
             "label2" : "aaaaa"
         }
       }
```

事件分析数据

```json
    {
      "country": "0",          /*所在国家*/
      "appVersion": "2.0",     /*APP版本*/
      "clientId": "amc-server",     /*终端ID*/
      "os": "Linux",     /*操作系统*/
      "city": "内网IP",     /*所在城市*/
      "mdVersion": "1.0.0",     /*行为分析插件版本*/
      "channel": "360应用商店",     /*渠道*/
      "datetimeLong": 1535096328234,     /*请求时间戳*/
      "xRealIp": "192.168.3.135",     /*请求IP*/
      "resolution": "1920x1080",     /*分辨率*/
      "url": "/test/page1",     /*事件发起页*/
      "facilitator": "内网IP",     /*运营商*/
      "uid": "cc3b62b4-ee14-4972-b025-1e0571bf0aa5",     /*会话ID*/
      "datetime": "2018-08-24T07:38:48.234Z",     /*请求时间*/
      "osVersion": "11.0",     /*操作系统版本*/
      "stayTime": 2222,     /*触发时长，负值表示最后一次动作*/
      "browser": "IE",     /*浏览器类型*/
      "browserVersion": "11",     /*浏览器版本*/
      "action": "",     /*细化行为*/
      "imei": "0000000-11",     /*设备ID*/
      "model": "X5",     /*设备型号*/
      "event": "风险评测",     /*事件类型*/
      "region": "0",     /*所在省份*/
      "brand": "中兴",     /*品牌*/
      "label":{
             "label1" : "xxxxx",
             "label2" : "aaaaa"
        }
    }

```