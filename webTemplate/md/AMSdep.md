[【返回目录】](../README.md)

## 微服务开发
#### 1.proto文件的定义
* proto协议定义请参考：[AMSdef.md](ams-protocol/README.md)；
* 定义好proto协议文件，使用`gradle`构建编译，将自动生成`service`对应的`grpc`代码，以及`message`对应的`protobuf`java对象；

#### 2.服务提供者的的开发样例
```java
//服务提供者类头部添加AmsService注解，对应服务版本号，以及服务所在的域（域参数可选）。域的作用是，例如当平台中多个系统都开放livebos标准微服务，这个时候通过域名来区分调用不同系统的标准服务；
//配置配置ams.server.namespace属性，代表当前节点所有服务都属于这域；
//服务提供者实现Grpc生成的base对象
@AmsService(version = "1.0.1")
//@AmsService(version = "1.0.1",namespace = "bss")
public class EsbAmsProducer extends EsbServiceGrpc.EsbServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(EsbAmsProducer.class);

    public void service(CommonRequest req, StreamObserver<CommonResponse> responseObserver){

        CommonResponse.Builder builder = CommonResponse.newBuilder();
        try {
            builder.setCode(1).setNote("[esb]success");
            int count = 10;
            for(int i=1;i<=count;i++){
                CommonRecord record = CommonRecord.newBuilder()
                        .setRow("row:"+i)
                        .setId(i)
                        .setAppCode("appcode:"+i)
                        .setAppName("appname:"+i)
                        .setAppDesc("appdesc:"+i)
                        .setAppStatus(1)
                        .build();
                builder.addRecords(record);
            }
            builder.setCount(count);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            builder.setCode(-1).setNote("[esb]error:"+e.getMessage());
        }finally {
            CommonResponse resp = builder.build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }
    }
    public void streamService(CommonRequest req, StreamObserver<StreamResponse> responseObserver){
        try {
            int count = 10;
            for(int i=1;i<=count;i++){
                StreamResponse.Builder builder = StreamResponse.newBuilder();
                builder.setCount(count);
                builder.setCode(1).setNote("[esb]success");
                CommonRecord record = CommonRecord.newBuilder()
                        .setRow("row:"+i)
                        .setId(i)
                        .setAppCode("appcode:"+i)
                        .setAppName("appname:"+i)
                        .setAppDesc("appdesc:"+i)
                        .setAppStatus(1)
                        .build();
                builder.setRecord(record);
                StreamResponse resp = builder.build();
                responseObserver.onNext(resp);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            StreamResponse.Builder builder = StreamResponse.newBuilder();
            builder.setCode(-1).setNote("[esb]error:"+e.getMessage());
            responseObserver.onNext(builder.build());
        }finally {
            responseObserver.onCompleted();
        }
    }
}

```

#### 3.服务消费者的开发样例
```java
@Service
public class EsbAmsConsumer {

    private static final Logger log = LoggerFactory.getLogger(EsbAmsConsumer.class);

    //通过注解的方式注入grpc客户端调用对象
    @AmsBlockingStub
    //@AmsNamespace("${example.ams.namespace:bss}")
    private EsbServiceGrpc.EsbServiceBlockingStub stub;

    public JSONObject doService(){
        try {
            CommonRequest req = CommonRequest.newBuilder()
                    .setServiceId("esb.ygt.xxxx")
                    .setSessionId("sessionId")
                    .setHeader("{'token':'aaaaaaaaa'}")
                    .setBody("{'name':'应用架构研发'}")
                    .build();
            CommonResponse resp = stub.service(req);
            return JSON.parseObject(JsonFormat.printer().print(resp));
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            JSONObject result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            return result;
        }

    }

    public JSONObject doStreamService(){
        try {
            CommonRequest req = CommonRequest.newBuilder()
                    .setServiceId("esb.ygt.xxxx")
                    .setSessionId("sessionId")
                    .setHeader("{'token':'aaaaaaaaa'}")
                    .setBody("{'name':'应用架构研发'}")
                    .build();
            Iterator<StreamResponse> it = stub.streamService(req);
            JSONArray records = new JSONArray();
            while(it.hasNext()){
                StreamResponse resp = it.next();
                JSONObject record = JSON.parseObject(JsonFormat.printer().print(resp.getRecord()));
                records.add(record);
            }
            JSONObject result = new JSONObject();
            result.put("code",-1);
            result.put("note","[esb.stream]ok");
            result.put("records",records);

            return result;
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            JSONObject result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            return result;
        }

    }

    //用代码的方式生成grpc客户端对象demo
    private  EsbServiceGrpc.EsbServiceBlockingStub getAmsStub() throws Exception {
        if (stub == null) {
            synchronized (this) {
                if (stub == null) {
                    //初始化grpc stub
                    stub = (EsbServiceGrpc.EsbServiceBlockingStub)AmsServiceManager.getInstance()
                            .newBlockingStub(SimpleServiceRefer.newRefer(EsbServiceGrpc.class));
                    //初始化grpc stub ，带域
                    stub = (EsbServiceGrpc.EsbServiceBlockingStub)AmsServiceManager.getInstance()
                            .newBlockingStub(SimpleServiceRefer.newRefer("namespace", EsbServiceGrpc.class));
                }
            }
        }
        return stub;
    }

}
```

[【返回目录】](../README.md)
