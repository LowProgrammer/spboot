package com.apexsoft.webapp.modules.download.service;

import com.alibaba.fastjson.JSONObject;
import com.apex.ams.annotation.AmsBlockingStub;
import com.apex.ams.test.common.DownloadRequest;
import com.apex.ams.test.common.DownloadResponse;
import com.apex.ams.test.DemoServiceGrpc;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Created on 2018/8/10.
 *
 * @author Sury
 */
@Service
public class DownloadService {
    @AmsBlockingStub
    private DemoServiceGrpc.DemoServiceBlockingStub stub;

    /*private  DemoServiceGrpc.DemoServiceBlockingStub getStub(){
        synchronized (this) {
            try{
                stub = (DemoServiceGrpc.DemoServiceBlockingStub)AmsServiceManager.getInstance()
                        .newBlockingStub(SimpleServiceRefer.newRefer("my_namespace",DemoServiceGrpc.class));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return stub;
    }*/

    public void download(JSONObject params, HttpServletResponse response){
        if(null==params){
            throw new RuntimeException("参数不能为空");
        }
        try {
            DownloadRequest.Builder builder = DownloadRequest.newBuilder();
            builder.setData(params.toJSONString());
            Iterator<DownloadResponse> iterator =  stub.download(builder.build());
            String fileName = "";
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            int length = 0;
            while(iterator.hasNext()){
                DownloadResponse resp = iterator.next();
                fileName = resp.getFilenname();
                if (resp.getCode()>0){
                    ByteString byteString = resp.getData();
                    byte[] b = byteString.toByteArray();
                    out.write(b);
                    length += b.length;
                }
            }
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + length);
            response.setContentType("application/octet-stream");

            out.flush();
            out.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }

    }

}
