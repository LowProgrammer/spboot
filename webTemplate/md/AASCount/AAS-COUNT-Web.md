# 顶点网页埋点控件 #


- 支持IE19-IE11,Chrome
- IE需要引入polyfill垫片
- <span color="red">如果浏览器是IE9,埋点采集服务器和业务服务器必须要相同，埋点数据才可采集</span>


# 埋点前端控件使用方法 #

## 1.埋点控件初始化 ##

1. 页面引用uba-1.0.3.js

    ```
       <script type="text/javascript" src="/x/xx/xxx/uba-1.0.3.js"></script>
    ```
2. IE在count-x.x.x.js前面引入babel-polyfill

    ```
       <script type="text/javascript" src="/x/xx/xxx/polyfill.min.js"></script>
     ```
2. 初始化控件

```javascript

ApexCountAction.config("http://127.0.0.1:8081", "dongdong@aas.count", "b61aa47f-cfb0-4c8f-b405-3f0c271708fb","test","1.0.0","测试渠道");

```

 ```javascript
    /**
         *  url:埋点服务端地址，填""表示和业务服务器一致
         *  countClientId:埋点服务ID
         *  countSecret:埋点服务秘钥
         *  clientId:业务终端ID
         *  appVersion:业务服务版本
         *  channel:渠道
         *
         * */
        config(url, countClientId, countSecret,clientId, appVersion, channel) {
 ```

## 2.页面访问行为记录 ##

埋点初始化好后，页面访问行为会自动上传到埋点服务器

## 3.事件行为记录 ##
事件行为需要硬编码调用，在调用发起事件之前写入

```javascript

     //事件记录，至于页面记录，只要引入md.js即可
     ApexCountAction.recordEvent("event1","事件名称","action1","{}");

```

```javascript
    /**
     *  event:事件类型
     *  eventName:事件类型
     *  action:具体动作
     *  ext:json格式字符串，扩展信息
     *
     * */
    recordEvent(event,eventName, action, ext) {
```
## 4.埋点事件增加自定义的维度标签 ##

为了让用户行为分析维度更多，对页面访问和事件行为都可以增加标签，其中，标签作用域有两种

- page ：单页面有效，跳往其他页面后，标签设置的值失效
- session :整个会话有效，只要在浏览器没关闭，标签设置的值一直有效

```javascript
    /**
     *  key:标签类型
     *  value:标签值
     *  scope:作用域，page,session两种
     *
     * */
    setLabel(key, value, scope) {
 ```

### 5.aas-spring-starter中内置了count-x.x.x.js，可以直接引用

```javascript
    <script type="text/javascript" src="webjars/js/polyfill.min.js"></script>
    <script type="text/javascript" src="webjars/js/count-1.0.3.js"></script>
```

# 全部示例代码 #

```javascript
    <script type="text/javascript" src="webjars/js/polyfill.min.js"></script>
    <script type="text/javascript" src="webjars/js/count-1.0.1.js"></script>
    <script type="text/javascript">
           //埋点初始化配置
         ApexCountAction.config("http://127.0.0.1:8081", "dongdong@aas.count", "b61aa47f-cfb0-4c8f-b405-3f0c271708fb","test","1.0.0","测试渠道");
         //设置标签，第三个参数是scope,可选访问page,session,
         ApexCountAction.setLabel("page", "1111");
         ApexCountAction.setLabel("session", "1111", "session");
         ApexCountAction.setLabel("session2", "1111", "session");
         //事件记录，至于页面记录，只要引入md.js即可
         ApexCountAction.recordEvent("event1","事件名称","action1","{}");
    </script>
```