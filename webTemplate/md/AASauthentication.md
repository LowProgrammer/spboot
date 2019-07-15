[【返回目录】](../README.md)
#AAS认证插件

## 插件描述

>AAS认证插件扩展了AAS框架的认证接口，实现了livebos和simple两种认证模式。
>livebos模式通过调用livebos平台的微服务接口进行用户认证；
>simple模式的用户认证逻辑需要自己实现；

## 插件使用说明

### livebos认证模式

>该模式通过调用livebos微服务方式实现，livebos需升级到支持微服务的版本，aas.livebos.ams.enable必须配置为true。

配置参数

```yaml
aas:
  authentication:
    enabled: true #是否启用插件
    type: livebos # 目前支持livebos、simple两种模式
  livebos:
    api-user: ws_user #必须是能访问ws服务的用户
    api-pwd: '000000' #用户密码
    ams:
      enabled: true #是否启用微服务
      namespace: lbtest #namespace值必须同livbos的系统配置参数【ams.server.namespace】值一致
```

#### 自定义返回用户信息
>livebos平台用户验证接口返回的用户信息比较少，如果有需要自定义返回用户信息，可以实现接口【CustomAuthUserService】

参考代码

```java
import com.apexsoft.aas.authentication.livebos.service.CustomAuthUserService;
import com.apexsoft.aas.common.exception.AuthException;
import com.apexsoft.aas.modules.index.model.AuthUser;
import org.springframework.stereotype.Service;

@Service
public class DDUserService implements CustomAuthUserService {
    @Override
    public AuthUser createAuthUser(String loginId) throws AuthException {
        AuthUser authUser = new AuthUser();
        //根据loginId去查询用户对应的信息，并设置到authUser对象中
        //authUser.setUserId();//必须设置
        //authUser.setUserName();//必须设置
        //authUser.setUser();
        return authUser;
    }
}
```
### simple认证模式

>1. 该模式需要结合aas控制台使用，使用前需先执行脚本scripts/aas-authentication.sql，
>2. 该模式认证逻辑需自行实现，必须implements接口com.apexsoft.aas.authentication.simple.LoginService，返回AuthUse对象
>3. 使用该模式可通过控制台配置角色、权限、组件，并给用户和角色分配权限，首次使用最好将用户数据导入到tb_user表，
>user_num（用户编号即userId）和user_name必须有值，user_attr存储用户其它属性，json格式，可为空。

配置参数

```yaml
aas:
  authentication:
    enabled: true #是否启用插件
    type: livebos # 目前支持livebos、simple两种模式
  livebos:
    api-user: ws_user #必须是能访问ws服务的用户
    api-pwd: '000000' #用户密码
    ws:
      enabled: false #是否启用webservice，ams优先
      address: http://127.0.0.1:8090 #livebos平台地址
```

参考代码

```java
import com.apexsoft.aas.authentication.simple.LoginService;
import com.apexsoft.aas.common.exception.AuthException;
import com.apexsoft.aas.common.session.UserSession;
import com.apexsoft.aas.modules.index.model.AuthData;
import com.apexsoft.aas.modules.index.model.AuthUser;
import com.apexsoft.aas.modules.index.service.IAuthUserService;
import com.apexsoft.webapp.modules.user.model.DDUser;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Primary
public class UserLoginService implements LoginService {
    private static Map<String,DDUser> userMap = new HashMap<>();
    static {
        userMap.put("admin",new DDUser("admin","用户admin"));
        userMap.put("10001",new DDUser("10001","用户10001"));
        userMap.put("10002",new DDUser("10002","用户10002"));
        userMap.put("10003",new DDUser("10003","用户10003"));
        userMap.put("10004",new DDUser("10004","用户10004"));
        userMap.put("10005",new DDUser("10005","用户10005"));
        userMap.put("10006",new DDUser("10006","用户10006"));
        userMap.put("10007",new DDUser("10007","用户10007"));
        userMap.put("10008",new DDUser("10008","用户10008"));
        userMap.put("10009",new DDUser("10009","用户10009"));
        userMap.put("10010",new DDUser("10010","用户10010"));
    }

    /**
     * 通过userId和密码获取用户
     * @param userId
     * @param password
     * @return
     */
    private DDUser getUser(String userId,String password){
        //这里简单用Map存放用户信息，真实场景应该是从DAO层获取数据
        DDUser user = userMap.get(userId);
        //校验密码，此处省略
        return user;
    }

    /**
     * 通过userId取用户
     * @param userId
     * @return
     */
    private DDUser getUser(String userId){
        //这里简单用Map存放用户信息，真实场景应该是从DAO层获取数据
        DDUser user = userMap.get(userId);
        return user;
    }

    private AuthUser createAuthUser(DDUser user){
        if(null==user){
            return null;
        }
        AuthUser authUser = new AuthUser();
        authUser.setUserId(user.getUserId());
        authUser.setUserName(user.getUserName());
        authUser.setUser(user);
        return authUser;
    }

    @Override
    public AuthUser login(AuthData authData) throws AuthException {
        //验证参数，如有其它参数需要验证，请求时将参数放到AuthData的ext中，这里取出来进行验证
        //如果需要用到HttpServletRequest对象，可以通过 UserSession.getRequestHolder() 获取
        if(null!=authData && StringUtils.hasText(authData.getUser())  && StringUtils.hasText(authData.getPassword())){
            DDUser user = getUser(authData.getUser(),authData.getPassword());
           return createAuthUser(user);
        }else{
            throw new AuthException("认证参数不能为空");
        }
    }
}

```
### livebos流程ticket服务

>AAS认证插件提供了livebos工作流程ticket服务的调用，在启用插件的情况下，只要livebos.ams.enabled=true或者livebos.ws.enabled=true时，
>可以通过功能码【aas.authentication.livebos.ticket】获取livebos的ticket访问url，如果livebos.ams.enabled、livebos.ws.enabled都设置为 true，优先使用微服务方式。
[【返回目录】](../README.md)
