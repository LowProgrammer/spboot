[【返回目录】](../README.md)
## RestFul接口开发
### 基于/service为公共入口的func(功能码)型服务

func型服务是AAS平台推出的快速服务开发模式，可以通过实现固定接口，
快速生成一个RestFul服务。并且通过定义输入和输出实体，自动形成接口定义文档。

1. 定义Service继承com.apexsoft.aas.commonBusinessApi

    示例中采用静态内部类的方式定义service服务，为了将让服务的输入和输出可以集中在一个类中，方便阅读。
    其实也可以将service服务定义成独立类。

2. 定义服务功能码，对Service增加@ApiService注解,平台才能访问其restful服务

        value : 功能码，该服务的唯一标识，不可重复（必填）
        tags : 服务分组，用于在swagger中分组展示
        name : 服务名称，用于展示服务中文说明
        request: 服务输入参数结构,默认值为JSONRequest.class
        response: 服务输出参数，默认值为JSONResponse.class

    ```java

    public class DemoService {

        @ApiService(value = "demo1.add", tags = "示例", name = "用例", request = TestRequest.class, response = JSONResponse.class)
        public static class TestService extends BusinessApi<TestRequest, JSONResponse> {

            @Override
            public JSONResponse exec(String func, String version, JSONObject jsonData, HttpServletRequest request, HttpServletResponse response) {
                return new JSONResponse(JSONResponse.CODE_SUCCESS, "测试");
            }
        }

    }

    ```
3. 定义输入参数(可选步骤),如果不想定义,可以在BusinessApi的第一个泛型参数设置为JSONRequest,不用自定义类

   - 服务入参类需继承com.apexsoft.aas.common.JSONRequest，并且增加@ApiModel(模型定义注解)和@ApiModelProperty(属性定义注解)
   - 将定义的输入参数填写在@ApiService的requst参数中，Swagger就可以按照实体的定义形成文档，不填写，不影响接口调用，只是影响文档展示

    ```
    @ApiModelProperty说明
    position : 排序，用于决定swagger中的展示位置
    notes : 字段中文名

    ```
    ```java
    public class DemoService {

        @ApiModel
        public static class TestRequest extends JSONRequest {
            @ApiModelProperty(position = 0, notes = "测试数据")
            private String test;

            public String getTest() {
                return test;
            }

            public void setTest(String test) {
                this.test = test;
            }
        }

    }

    ```

4. 定义输出参数(可选步骤),如果不想定义,可以在BusinessApi的第二个泛型参数设置为JSONResponse,不用自定义类

   - 服务入参类需继承com.apexsoft.aas.common.JSONResponse，并且增加@ApiModel(模型定义注解)和@ApiModelProperty(属性定义注解)
   - 将定义的输入参数填写在@ApiService的response参数中，Swagger就可以按照实体的定义形成文档，不填写，不影响接口调用，只是影响文档展示

    ```
    @ApiModelProperty说明
    position : 排序，用于决定swagger中的展示位置
    notes : 字段中文名

    ```
    ```java
    public class DemoService {

        @ApiModel
        public static class TestResponse extends JSONResponse {
            @ApiModelProperty(position = 0, notes = "测试数据")
            private String result;

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }
        }

    }

    ```

5. 接口定义完成，编译重启服务，可以在线查看API和测试

    > 具体用法查看[Swagger插件使用](AASSession.md "目录")
6. 接口访问授权

    > 目前平台默认规则是func服务需要登录，才可以访问，如果不想登录后访问，具体配置修改查看[会话控制插件](README.md "目录")


### 基于SpringMVC-Controller的PATH服务

1. PATH服务就是采用SpringMVC的controller控制器开发的服务，具体示例参考如下

    ```java
    @Controller
    public class DownloadController {
        @Autowired
        private DownloadService downloadService;
        @ApiOperation(value = "下载文件入口页")
        @RequestMapping("/file")
        public String file(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
            return "demo/file";
        }

        @RequestMapping("/download")
        public String download(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
            String fileId = request.getParameter("fileId");
            JSONObject params = new JSONObject();
            params.put("fileId",fileId);
            downloadService.download(params,response);
            return "";
        }
    }

    ```

2. 如果需要在Swagger平台上生成API文档，在方法上增加@ApiOperation注解
    具体配置参考[Swagger-ui官方文档](https://swagger.io/tools/swagger-ui/ "目录")

3. 接口定义完成，编译重启服务，可以在线查看API和测试
    > 具体用法查看[Swagger插件使用](README.md "目录")
4. 接口访问授权
    > 目前平台默认规则是PATH服务不需要登录，就可以访问，如果希望登录后访问，具体配置修改查看[会话控制插件](README.md "目录")

[【返回目录】](../README.md)
