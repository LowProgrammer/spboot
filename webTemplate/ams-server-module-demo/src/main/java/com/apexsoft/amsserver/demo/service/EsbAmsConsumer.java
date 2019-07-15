package com.apexsoft.amsserver.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apex.ams.annotation.AmsBlockingStub;
import com.apex.ams.client.AmsServiceManager;
import com.apex.ams.client.SimpleServiceRefer;
import com.apex.ams.esb.EsbServiceGrpc;
import com.apex.ams.test.common.CommonRequest;
import com.apex.ams.test.common.CommonResponse;
import com.apex.ams.test.common.StreamResponse;
import com.google.protobuf.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;

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
                    //初始化grpc stub
//                    stub = (EsbServiceGrpc.EsbServiceBlockingStub)AmsServiceManager.getInstance()
//                            .newBlockingStub(SimpleServiceRefer.newRefer("namespace", EsbServiceGrpc.class));
                }
            }
        }
        return stub;
    }

}
