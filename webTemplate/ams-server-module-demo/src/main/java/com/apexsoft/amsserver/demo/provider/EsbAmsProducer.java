package com.apexsoft.amsserver.demo.provider;

import com.apex.ams.test.common.*;
import com.apex.ams.esb.EsbServiceGrpc;
import com.apex.ams.server.AmsService;
import com.apex.ams.test.common.CommonRecord;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午10:56 2018/2/9
 * @MODIFIED BY:
 */
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
