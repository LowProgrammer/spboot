package com.apexsoft.amsserver.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apex.ams.annotation.AmsBlockingStub;
import com.apex.ams.test.DemoServiceGrpc;
import com.apex.ams.test.common.CommonRequest;
import com.apex.ams.test.common.CommonResponse;
import com.google.protobuf.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AasDemoConsumer {

    private static final Logger log = LoggerFactory.getLogger(AasDemoConsumer.class);

    //通过注解的方式注入grpc客户端调用对象
    @AmsBlockingStub
    private DemoServiceGrpc.DemoServiceBlockingStub stub;

    public JSONObject hello(){
        try {
            CommonRequest req = CommonRequest.newBuilder()
                    .setServiceId("aas.test.demo")
                    .setSessionId("sessionId")
                    .setHeader("{'token':'hello world'}")
                    .setBody("{'name':'dinglei'}")
                    .build();
            CommonResponse resp = stub.hello(req);
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

}
