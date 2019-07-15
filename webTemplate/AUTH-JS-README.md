# React调用示例 #

配置npm到公司私服



	npm config set registry http://oss.apexsoft.com.cn/repository/react-native-group/

package.json导入依赖：

	"dependencies": {
	    "@antv/data-set": "^0.8.7",
	    "@babel/polyfill": "^7.0.0-beta.46",
	    "antd": "^3.5.0",
	    "aas-aes-js": "^1.0.2",

编码：

	import {AES} from 'aas-aes-js';
	AES.setSecret(APP_SECRET);
	/*加密*/
  	let signature = AES.encryptBase64(query);

# 传统JS调用示例 #





传统的JS已经集成到aas-common.jar包中，可以直接使用如下地址引用

> /webjars/js/aes-1.0.2.js
	



	<script type="text/javascript" src="aes-1.0.2.js"></script>
	<script type="text/javascript">
      /**设置秘钥**/
	  AES.setSecret("111111");
	  /**加密**/
	  var encode = AES.encryptBase64("1111");
	  console.log(encode);
	  console.log(decode);
	</script>