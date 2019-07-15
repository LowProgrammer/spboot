[【返回目录】](../README.md)
# 第一个web应用

> web应用开发参考模块webapp-base

### 传统JSP前端
* 构建脚本中，必须引入依赖嵌入式tomcat对jsp的支持
```
compile('org.apache.tomcat.embed:tomcat-embed-jasper')
```
* controller层的开发和SpringMVC的开发写法完全一样，jsp页面默认存放路径`webapp-base/src/main/webapp/WEB-INF/demo/welcome.jsp`
```
@Controller
public class JspDemoController {

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, ModelMap model){
        return "demo/welcome";
    }
}
```


### JS前端框架（前后端分离）
* 这种模式，服务端开发跟开发纯restAPI是一样的
* 前端建议统一采用Ant Design开发框架
```
@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String hello() throws Exception{
        JSONObject json = new JSONObject();
        json.put("code",1);
        json.put("note","hello world");
        return json.toJSONString();
    }
}
```

[【返回目录】](../README.md)
