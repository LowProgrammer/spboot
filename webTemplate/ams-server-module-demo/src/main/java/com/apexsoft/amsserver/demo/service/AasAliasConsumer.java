package com.apexsoft.amsserver.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.apex.ams.annotation.AmsAlias;
import com.apex.ams.annotation.AmsBlockingStub;
import com.apex.ams.annotation.AmsNamespace;
import com.apex.ams.common.CommRequest;
import com.apex.ams.common.CommResponse;
import com.apex.ams.common.CommonGrpc;
import com.apex.ams.common.DownloadFileInfo;
import com.apex.ams.util.AasCommService;
import com.apex.ams.util.AmsMessageUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created on 2018/10/16.
 *
 * @author Sury
 */
@Service
public class AasAliasConsumer {
    @AmsBlockingStub
    @AmsNamespace("aas.demo")
    @AmsAlias("alias.test@aas.webtemplate")
    private CommonGrpc.CommonBlockingStub stub;

    public void hello(){
        CommRequest.Builder builder = CommRequest.newBuilder();
        builder.putParam("name",AmsMessageUtils.toAny("lilei"));

        CommResponse resp = stub.handle(builder.build());

        JSONObject result = AmsMessageUtils.toJSONObject(resp.getDataMap());

        System.out.println(result.getString("data"));

    }

    /**
     * 使用工具类AasCommService调用微服务
     */
    public void hello2(){
        JSONObject reqJson = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("name","lilei");
        reqJson.put("param",param);
        JSONObject resp = AasCommService.sendRequest("aas.demo","alias.test@aas.webtemplate",reqJson);
        System.out.println(resp.toJSONString());
    }

    /**
     * 使用工具类AasCommService调用微服务上传文件
     * @throws Exception
     */
    public void uploadFile() throws Exception{
        JSONObject fileInfo = new JSONObject();
        fileInfo.put("fileName","mist-0.9.0.zip");
        File file = new File("d:/soft/mist-0.9.0.zip");
        JSONObject resp = AasCommService.uploadFile("aas.demo","alias.test@aas.webtemplate",fileInfo,new FileInputStream(file));
        System.out.println(resp);
    }

    /**
     * 使用工具类AasCommService调用微服务下载文件
     * @throws Exception
     */
    public void downloadFile() throws Exception{
        JSONObject fileInfo = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("fileId","11");
        fileInfo.put("param",param);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DownloadFileInfo downloadFileInfo = AasCommService.downloadFile("aas.demo","alias.test@aas.webtemplate",fileInfo,bos);
        FileOutputStream fos = new FileOutputStream("d:/"+downloadFileInfo.getFileName());
        fos.write(bos.toByteArray());
        System.out.println(downloadFileInfo.getFileName());
    }

}
