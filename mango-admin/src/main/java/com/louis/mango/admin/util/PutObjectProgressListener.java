package com.louis.mango.admin.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import  com.aliyun.oss.OSSClientBuilder ;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.louis.mango.admin.util.AliyunOSSClientUtil.getContentType;

/**
 * @Description: 进度条（上传文件监听器）
 * @Author: created by wangkaishuang on 2019-08-02
 */

public class PutObjectProgressListener implements ProgressListener {

    private long bytesWritten = 0;
    private long totalBytes = -1;
    private boolean succeed = false;

    private static final Logger log = LoggerFactory.getLogger(PutObjectProgressListener.class);

    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to upload......");
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
                this.totalBytes = bytes;
                System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                    System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                break;
            case TRANSFER_FAILED_EVENT:
                System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                break;
            default:
                break;
        }
    }

    public boolean isSucceed() {
        return succeed;
    }

    public static void main(String[] args) {
        String endpoint = OSSClientConstants.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = OSSClientConstants.ACCESS_KEY_ID;
        String accessKeySecret = OSSClientConstants.ACCESS_KEY_SECRET;
        String bucketName = OSSClientConstants.BACKET_NAME;
        String objectName = OSSClientConstants.FOLDER;

        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        try {
            // 带进度条的上传。
//            ossClient.putObject(new PutObjectRequest(bucketName, objectName, new File("/Users/ksmaster/Desktop/GD.zip")).
//                    <PutObjectRequest>withProgressListener(new PutObjectProgressListener()));

            String files = "/Users/ksmaster/Desktop/avatar.png";
            File file = new File(files);
            AliyunOSSClientUtil.uploadObject2OSS(ossClient, file, bucketName, objectName);
//            ossClient.putObject(new PutObjectRequest(bucketName, objectName, file).
//                    <PutObjectRequest>withProgressListener(new PutObjectProgressListener()));
//            log.info("Etag：{}", putObjectResult.getETag());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
