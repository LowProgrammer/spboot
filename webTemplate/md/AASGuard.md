[【返回目录】](../README.md)
## 请求数据加密器

1. 如果API请求需要做二次加密，可以采用AESCryptoServiceHandler加密器，也可以自定义加密器

    启用AES加密，可以SpringBoot中配置AESCryptoServiceHandlerBean,如下所示

    ```java

        @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
        @ComponentScan(basePackages = "com.apexsoft")
        public class AasApplication {
            public static void main(String[] args){
                SpringApplication.run(AasApplication.class, args);
            }


            @Bean
            public AESCryptoServiceHandler serviceHandler(){
                return  new AESCryptoServiceHandler();
            }
        }


    ```


2. 如果需要实现自定义请求处理器，可以实现IServiceHandler接口

    ```java
       public interface IServiceHandler {

        JSONObject preHandle(String func, String version, HttpServletRequest request, HttpServletResponse response) throws ServiceHandleException;

        JSONResponse postHandle(JSONResponse jsonResponse, HttpServletRequest request, HttpServletResponse response);
      }
    ```


- preHandle 是实现对请求数据的解密
- postHandle是实现对响应数据的加密
- 目前内置AES变种对称加密算法，也可以自实现加密算法

[【返回目录】](../README.md)
