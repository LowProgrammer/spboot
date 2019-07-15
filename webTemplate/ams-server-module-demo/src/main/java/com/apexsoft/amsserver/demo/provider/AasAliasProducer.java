package com.apexsoft.amsserver.demo.provider;

import com.alibaba.fastjson.JSONObject;
import com.apex.ams.common.*;
import com.apex.ams.server.AmsService;
import com.apex.ams.util.AmsMessageUtils;
import com.apex.ams.util.DownloadResponseUtils;
import com.apex.ams.util.UploadStreamObserver;
import io.grpc.stub.StreamObserver;

import java.io.*;

/**
 * Created on 2018/10/16.
 *
 * @author Sury
 */
@AmsService(namespace = "aas.demo",alias="alias.test@aas.webtemplate")
public class AasAliasProducer  extends CommonGrpc.CommonImplBase {
    @Override
    public void handle(CommRequest request, StreamObserver<CommResponse> responseObserver) {
        JSONObject param = AmsMessageUtils.toJSONObject(request.getParamMap());

        String name = param.getString("name");

        CommResponse.Builder builder = CommResponse.newBuilder();

        builder.setCode(AmsMessageUtils.CODE_SUCCESS);

        builder.putData("data",AmsMessageUtils.toAny("hello,"+name));

        responseObserver.onNext(builder.build());

        responseObserver.onCompleted();

    }

    /**
     * 根据fileId获取文件流，并返回文件名称
     * @param fileId
     * @param bos
     * @return
     */
    private String getFileOutputStream(String fileId,ByteArrayOutputStream bos){
        //参考AasDemoProducer中的download
        File file = new File("d:/soft/mist-0.9.0.zip");
        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bs)) != -1) {
                bos.write(bs,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    @Override
    public void download(DownloadRequest request, StreamObserver<DownloadResponse> responseObserver) {
        DownloadFileInfo.Builder fileInfoBuilder = DownloadFileInfo.newBuilder();
        JSONObject param = AmsMessageUtils.toJSONObject(request.getParamMap());
        String fileId = param.getString("fileId");
        //更加fielId获取文件流和文件信息
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String fileName = getFileOutputStream(fileId,bos);
        fileInfoBuilder.setCode(AmsMessageUtils.CODE_SUCCESS);
        fileInfoBuilder.setFileName(fileName);
        fileInfoBuilder.setFileLength(bos.size());
        DownloadResponseUtils.response(responseObserver,fileInfoBuilder.build(),bos.toByteArray());
    }

    @Override
    public StreamObserver<UploadRequest> upload(StreamObserver<UploadResponse> responseObserver) {
        return new UploadStreamObserver(responseObserver) {

            /**
             * 保存文件并返回filecode
             * @param is
             * @return
             */
            private String save(InputStream is){
                //保存文件
                try{
                    UploadFileInfo fileInfo = getFileInfo();
                    FileOutputStream fos = new FileOutputStream("d:/"+fileInfo.getFileName());
                    BufferedInputStream inputStream = new BufferedInputStream(is);
                    byte[] bs = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(bs)) != -1) {
                        fos.write(bs,0,len);
                    }
                    fos.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {

                }
                return "111";
            }

            @Override
            public UploadResponse.Builder upload(InputStream inputStream) {
                UploadResponse.Builder builder = UploadResponse.newBuilder();
                String filecode = save(inputStream);
                builder.setCode(AmsMessageUtils.CODE_SUCCESS).setFilecode(filecode);

                return builder;
            }
        };
    }
}
