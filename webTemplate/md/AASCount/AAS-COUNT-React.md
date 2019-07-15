# 埋点前端库 #
## 1. 调用代码 ##

配置npm到公司私服
```
npm config set registry http://oss.apexsoft.com.cn/repository/react-native-group/
```

package.json导入依赖：

```
	"dependencies": {
	    "aas-uba-react": "^1.0.7",
```

```
		import  {ApexCountAction} from 'aas-uba-react';
	
		//只初始化一次，放在APP.js或者index.js的入口文件中	
		ApexCountAction.config("http://127.0.0.1:8081", "dongdong@aas.count", "b61aa47f-cfb0-4c8f-b405-3f0c271708fb","test","1.0.0","测试渠道",6000);

		
		

		//设置行为标签，
		ApexCountAction.setLabel("页面标签1", "1111");
	    ApexCountAction.setLabel("会话标签1", "1111", "session");
		

		//页面访问记录，在页面跳转的前置中调用	
		ApexCountAction.recordPage("访问页面","上一级页面","扩展信息");


		//事件行为记录，在事件的前置中调用	
		 ApexCountAction.recordEvent("event1","事件名称","action1","xx");
```

## 2. API说明 ##
1. 埋点控件初始化

```
		/**
         *  url:埋点服务端地址，填""表示和业务服务器一致
         *  countClientId:埋点服务ID
         *  countSecret:埋点服务秘钥
         *  clientId:业务终端ID
         *  appVersion:业务服务版本
         *  channel:渠道
         *  timeInterval:上传日志时间间隔，单位秒。默认值5秒
         * */
        config(url, countClientId, countSecret,clientId, appVersion, channel, timeInterval) {
```
- . 初始化行为头部

```
		/**
         * 设置埋点报文公共头部，只需要设置一次
         *  header.brand:设备品牌(非必填)
         *  header.model:设备型号(非必填)
         *  header.os:操作系统(非必填)
         *  header.osVersion:操作系统版本(非必填)
         *  header.facilitator:运营商(非必填)
         *  header.reactVersion:React版本(必填)
         *  header.browser:浏览器类型(非必填)
         *  header.browserVersion:浏览器版本(非必填)
         *  header.imei:设备ID（IMEI或者MAC）(非必填)
         *
         * */
        setHeader(header) {
```

- .设置行为标签，page级别标签再每次调用recordPage之后，失效

```
		/**
	     *  key:标签类型
	     *  value:标签值
	     *  scope:作用域，page,session两种
	     *
	     * */
	    setLabel(key, value, scope) {
```

- .设置业务用户ID

```
		/**
	     *  设置业务用户ID
	     *  userId:用户ID
	     *
	     * */
	    setUserId(userId) {
```

- .页面访问记录

```
		/**
             *  页面访问记录
             *  url:埋点服务端地址
             *  ref_url:上一级页面
             *  ext:扩展信息
             * */
            recordPage(url, ref_url, ext) {
```

- .事件行为记录

```
		 /**
         *  时间行为记录
         *  event:事件类型
         *  eventName:事件类型名称
         *  action:具体动作
         *  ext:json格式字符串，扩展信息
         *  url:事件调用页
         *
         * */
        recordEvent(event,eventName, action, ext, url) {
```