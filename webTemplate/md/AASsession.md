[【返回目录】](../README.md)

## Session插件

### 1.统一认证接口

集成aas-springboot-starter,所有登录认证接口走/auth，restFul入参如下

- mode (string, optional): 认证模式(非必填)，默认值:user
	- 用户模式,需要传user或者不传，password不为空,需要实现IAuthService，
	- 其他认证模式自定义，实现IAuthServiceNotForUserMode,bean的名称=认证模式即可(必填|)
- clientId  (string, optional): 终端ID（必填）,由管理平台分配，于appSecret(秘钥)配对存在
- signature (string, optional): 签名串:将[user=${用户名}&password=${密码}&ext=${扩展数据}&timestamp=${当前时间戳}]用appSecret作为KEY，采用aas-ase-js控件加密所得值(必填)
- ext (string, optional): 扩展数据，JSON格式，存储appVersion，channel，resolution,可以不填

### 2.用户模式认证实现

继承IAuthService,实现auth方法，就可以实现用户默认的认证,
如果未实现用户模式认证的，系统将采用默认认证（填写任何用户名密码都能登录成功）

```java
		@Component
		public class DemoAuthService extends IAuthService{
		    @Override
		    public AuthResponse auth(AuthData authData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthException {
		        AuthUser authUser = new AuthUser();
		        authUser.setAccessToken(UserSession.getAccessToken());
		        authUser.setClientId(authData.getClientId());
		        return new AuthResponse(JSONResponse.CODE_SUCCESS, "这是一个测试的认证", authUser);

		    }
		}
```


### 3.页面访问白名单

如果希望重新定义访问规则，需要继承SessionWhitePage

- 如果没有实现SessionWhitePage，平台启用默认白名单，所有页面都可以访问，所有功能码都需要登录才可以访问
- 如果需要自定义白名单页面，SessionWhitePage中使用pageList添加免登录页面，使用funcList添加免登录功能码

```java
		@Component
		public class WhitePage extends SessionWhitePage {
		    //设置不用登录就可以访问的页面
		    @Override
		    protected void buildPage() {
		        pageList.add("*");
		    }

		    //设置不用登录就可以访问的功能码
		    @Override
		    protected void buildFunc() {
		        funcList.add("*");
		    }
		}
```

### 4.认证接口测试

 启动Swagger插件后，使用auth接口做测试


### 5.其他模式认证实现

平台的主认证模式是用户模式，如果有些业务场景，需要支持多种类型用户登录，并且登录后，可访问页面还不同

> 场景1：系统需要支持普通用户和管理员用户访问，同时他们之间能访问的页面又不同，这时候，就需要在访问不同页面时，验证对应用户是否登录

具体只要实现ICustomModeAuthService抽象类即可

- auth函数与IAuheService用法一致
- authPattern函数是指定哪些页面访问需要采用该认证模式检查，不指定的话，没有页面走该模式
- authFunc函数是指哪些功能的访问需要采用该认证模式检查，不指定的话，没有功能走该模式
- 如果定义了多个其他认证模式，其中指定的功能码和页面存在重叠，那个只要任意一个符合要求的认证模式是登录过的，就可以允许访问
- 如下示例，mode=md,和bean一致
```java
		@Component(MdAuthService.KEY_MD)
		public class MdAuthService extends ICustomModeAuthService {
		    public static final String KEY_MD = "md";

		    @Override
		    public AuthResponse auth(AuthData authData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthException {
		        AuthUser authUser =new AuthUser();
		        authUser.setAppIdentity(authData.getAppIdentity());
		        authUser.setAccessToken(UserSession.getAccessToken());
		        UserSession.setUserSession(KEY_MD,authUser);
		        JSONObject extJson =authData.getExt();
		        httpServletResponse.addCookie(new Cookie("appVersion", extJson.getString("appVersion")));
		        httpServletResponse.addCookie(new Cookie("channel", extJson.getString("channel")));
		        httpServletResponse.addCookie(new Cookie("resolution", extJson.getString("resolution")));
		        httpServletResponse.addCookie(new Cookie("appIdentity", authData.getAppIdentity()));
		        return new AuthResponse(JSONResponse.CODE_SUCCESS,"埋点认证成功",UserSession.getAccessToken());
		    }

		    @Override
		    protected void authPattern(List<String> list) {
		        list.add("/wt/event/record");
		        list.add("/wt/page/record");
		    }

		    @Override
		    protected void authFunc(List<String> list) {
		        list.add("wt.page.record");
		        list.add("wt.event.record");
		        list.add("wt.crash.record");
		    }
		}
```

### 6.认证会话检查顺序

浏览器访问http://127.0.0.1:8080/aa/bb/cc

1.	检查WhithPage白名单，是否匹配/aa/bb/cc的规则
	- true:停止后续检查，跳到步骤5
	- false:走步骤2
2. 检查其他认证模式，是否匹配/aa/bb/cc的规则，如果有多个其他认证模式，检查顺序随机，所有符合规则的模式，都进行步骤3判断
	- true：跳到步骤3
	- false:跳到步骤4
3. 检查/aa/bb/cc匹配中的认证模式，用户是否登录（存在多个符合条件的模式，对其结果采用或运算）
	- true:已登录，跳到步骤5
	- false:未登录，跳到步骤6
4. 检查用户模式下，用户是否登录
	- true:已登录，跳到步骤5
	- false:未登录，跳到步骤6
5. 访问目标页面
6. 返回900状态码，提示会话超时


### 7.LiveBos或者Simple模式

1-6章节提供的是底层会话实现方式，需要开发者实现认证逻辑，构建用户体系。

LiveBos或者Simple模式提供的是更高级的用户认证封装，简化了用户认证的实现难度，建议以上两种模式二选一，

### 8.ticket凭证
>允许外部应用通过ticket凭证访问服务或者页面。需实现接口【com.apexsoft.aas.modules.index.service.IAuthUserService】。
>ticket凭证有效时间30秒。

配置参数

```yaml
aas:
  ticket:
    enabled: true  #true开启ticket凭证访问，默认false
    secret: '1111111111111111' #访问密码
```

参考代码

```java
import com.apexsoft.aas.common.exception.AuthException;
import com.apexsoft.aas.modules.index.model.AuthUser;
import com.apexsoft.aas.modules.index.service.IAuthUserService;
import org.springframework.stereotype.Service;

@Service
public class DDUserService implements IAuthUserService {
    @Override
    public AuthUser createAuthUser(String loginId) throws AuthException {
        AuthUser authUser = new AuthUser();
        //根据loginId去查询用户对应的信息，并设置到authUser对象中
        //authUser.setUserId();
        //authUser.setUserName();
        //authUser.setUser();
        return authUser;
    }
}
```

>外部应用访问需按照以下步骤进行：
>1. 调用http://ip:port/ticket/register进行注册，参数：｛url:'',userId:''｝,其中userId需使用aas.ticket.secret进行加密
>2. 访问第一步返回的serviceUrl（相对地址），此时如果第1步参数url不为空时，将会跳转到该url（使用response.sendRedirect进行跳转）

参考代码

```java

import com.alibaba.fastjson.JSONObject;
import com.apexsoft.aas.common.http.HttpRequest;
import com.apexsoft.aas.common.http.OkHttpClientConfig;
import com.apexsoft.util.AES;

public class AasTicketTest {
    public static void main(String[] args) {
        JSONObject in = new JSONObject();
        //使用密钥对userId进行加密，密钥值同aas.ticket.secret
        in.put("userId", AES.encrypt("10001","1111111111111111"));

        String context = "http://127.0.0.1:7070/dd";
        OkHttpClientConfig config = new OkHttpClientConfig();
        HttpRequest httpRequest = new HttpRequest(config.okHttpClient());
        //1、注册
        JSONObject resp = httpRequest.post(context+"/ticket/register",in.toJSONString());
        System.out.println(resp.toJSONString());
        //2、获取注册时返回serviceUrl，并进行访问
        String url = resp.getString("serviceUrl");
        resp = httpRequest.post(context+url);
        System.out.println(resp.toJSONString());
        //3、访问服务
        JSONObject headers = new JSONObject();
        headers.put("func","demo.add");
        headers.put("version","");
        in = new JSONObject();
        in.put("test", "string");
        resp = httpRequest.post(context+"/service",in.toJSONString(),headers);
        System.out.println(resp.toJSONString());
    }
}
```

[【返回目录】](../README.md)
