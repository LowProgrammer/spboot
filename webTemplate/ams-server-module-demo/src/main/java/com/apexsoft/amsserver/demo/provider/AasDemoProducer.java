package com.apexsoft.amsserver.demo.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apex.ams.test.common.*;
import com.apex.ams.server.AmsService;
import com.apex.ams.test.DemoServiceGrpc;
import com.apexsoft.aas.common.JSONResponse;
import com.apexsoft.aas.common.exception.StreamException;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午10:56 2018/2/9
 * @MODIFIED BY:
 */
@AmsService(version = "1.0.1")
public class AasDemoProducer extends DemoServiceGrpc.DemoServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(AasDemoProducer.class);

    @Override
    public void hello(CommonRequest req, StreamObserver<CommonResponse> responseObserver){

        CommonResponse.Builder builder = CommonResponse.newBuilder();
        try {
            builder.setCode(1).setNote("[aas]demo ok!");
            int count = 10;
            for(int i=1;i<=count;i++){
                CommonRecord record = CommonRecord.newBuilder()
                        .setRow("row:"+i)
                        .setId(i)
                        .setAppCode("appcode:"+i + " "+ req.getServiceId())
                        .setAppName("appname:"+i+ " "+ req.getSessionId())
                        .setAppDesc("appdesc:"+i +" "+ req.getBody())
                        .setAppStatus(req.getHeader().length())
                        .build();
                builder.addRecords(record);
            }
            builder.setCount(count);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            builder.setCode(-1).setNote("[aas]error:"+e.getMessage());
        }finally {
            CommonResponse resp = builder.build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }
    }

    private String writeFileData(OutputStream destStream)throws StreamException {
        File file = new File("d:/ios_push.p12");
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            byte[] buff = new byte[8 * 1024];
            int len;
            while ((len = fis.read(buff)) != -1) {
                destStream.write(buff, 0, len);
            }
            return file.getName();
        }catch (Exception e){
            throw new StreamException(e.getMessage(), e);
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
            if (destStream != null) {
                try {
                    destStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public StreamObserver<com.apex.ams.test.common.UploadRequest> upload(StreamObserver<UploadResponse> responseObserver) {
        return new StreamObserver<UploadRequest>(){
            String filename;
            ByteArrayOutputStream bos;
            @Override
            public void onNext(UploadRequest request) {
                byte[] byteArray = request.getData().toByteArray();
                filename = request.getFilename();
                try {
                    if (bos == null) {
                        bos = new ByteArrayOutputStream();
                    }
                    bos.write(byteArray);
                    bos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                UploadResponse.Builder builder =  UploadResponse.newBuilder();
                builder.setCode(JSONResponse.CODE_FAIL).setNote(t.getMessage());
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                UploadResponse.Builder builder =  UploadResponse.newBuilder();
                try{
                    byte[] buff = bos.toByteArray();
                    String filepath ="E:\\" + filename;
                    File file  = new File(filepath);
                    if(file.exists()){
                        file.delete();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(buff,0,buff.length);
                    fos.flush();
                    fos.close();
                    builder.setCode(JSONResponse.CODE_SUCCESS);
                }catch (Exception ex){
                    ex.printStackTrace();
                    builder.setCode(JSONResponse.CODE_FAIL).setNote(ex.getMessage());
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        bos = null;
                    }
                }
            }
        };
    }

    @Override
    public void download(DownloadRequest request, StreamObserver<DownloadResponse> responseObserver) {
        DownloadResponse.Builder builder = DownloadResponse.newBuilder();
        try{
            //将请求参数转成JSON对象
            JSONObject json = JSON.parseObject(request.getData());
            String fileId = json.getString("fileId");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //可以根据fileId从数据库取出的CLOB字段的二进制内容放到bos中
            //这里直接用读文件代替
            String filename = writeFileData(bos);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len=bis.read(bs)) != -1) {
                ByteString byteString = ByteString.copyFrom(bs,0,len);
                builder.setCode(JSONResponse.CODE_SUCCESS).setFilenname(filename).setData(byteString);
                responseObserver.onNext(builder.build());
            }
            responseObserver.onCompleted();
        }catch (Exception ex){
            ex.printStackTrace();
            builder.setCode(JSONResponse.CODE_FAIL);
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        }

    }
}
