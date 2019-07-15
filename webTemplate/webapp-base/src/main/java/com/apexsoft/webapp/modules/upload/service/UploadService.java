package com.apexsoft.webapp.modules.upload.service;

import com.apex.ams.client.AmsServiceManager;
import com.apex.ams.client.SimpleServiceRefer;
import com.apex.ams.test.common.UploadRequest;
import com.apex.ams.test.common.UploadResponse;
import com.apex.ams.test.DemoServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

/**
 * Created on 2018/8/14.
 *
 * @author Sury
 */
@Service
public class UploadService {
    private DemoServiceGrpc.DemoServiceStub stub;

    private  DemoServiceGrpc.DemoServiceStub getStub(){
        synchronized (this) {
            try{
                stub = (DemoServiceGrpc.DemoServiceStub)AmsServiceManager.getInstance()
                        .newStub(SimpleServiceRefer.newRefer(DemoServiceGrpc.class));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return stub;
    }
    public void upload(String fileName, InputStream fileInputStream){
        final CountDownLatch count = new CountDownLatch(1);
        UploadStreamObserver responseObserver = new UploadStreamObserver(count);
        StreamObserver<UploadRequest> requestObserver = getStub().upload(responseObserver);
        try {
            try {
                BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
                byte[] bs = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bs)) != -1) {
                    ByteString byteString = ByteString.copyFrom(bs,0,len);
                    UploadRequest request = UploadRequest.newBuilder().setFilename(fileName).setData(byteString).build();
                    requestObserver.onNext(request);
                }
                requestObserver.onCompleted();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private static class UploadStreamObserver implements StreamObserver<UploadResponse> {
        private CountDownLatch count;
        public UploadStreamObserver(CountDownLatch count){
            this.count = count;
        }

        int code;
        String note;
        @Override
        public void onNext(UploadResponse value) {
            code = value.getCode();
            note = value.getNote();
            count.countDown();
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }

        public int getCode() {
            return code;
        }

        public String getNote() {
            return note;
        }
    }
}
