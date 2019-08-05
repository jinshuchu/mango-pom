package com.louis.mango.admin.service.impl;

import com.aliyun.oss.OSSClient;
import com.louis.mango.admin.dao.PhotoMapper;
import com.louis.mango.admin.model.Photo;
import com.louis.mango.admin.service.PhotoService;
import com.louis.mango.admin.util.AliyunOSSClientUtil;
import com.louis.mango.admin.util.OSSClientConstants;
import com.louis.mango.core.http.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-31
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;
    @Value("spring.servlet.multipart.location")
    private String UPLOADED_FOLDER;
    private final Double KB = 1024.0;

    private static final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Override
    public HttpResult save(MultipartFile file, Long userId) {
        if (file.isEmpty()) {
            return HttpResult.error(0, "无效文件");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("文件的后缀名为：" + suffixName);
        // 设置文件存储路径
        String filePath = "/Users/ksmaster/Desktop/photo/";
        String path = filePath + fileName;
        Photo photo = Photo.builder().resolutionRatio(photoRatio(file))
                .size(photoSize(file))
                .createTime(new Date())
                .path(path)
                .userId(userId)
                .classification("0")
                .tag("0")
                .burnAfterReading(0)
                .commentsNumber(0)
                .communicationStep(0)
                .likeNumber(0)
                .numberOfShare(0)
                .photoName(fileName)
                .build();
        photoMapper.insert(photo);

        try {
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建文件夹
                dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
        } catch (IOException e) {
            return HttpResult.error(0, "上传文件出错！");
        }
        return HttpResult.ok(photo);
    }

    @Override
    public HttpResult getPhotoByClassification(Long userId, String classification) {
        return HttpResult.ok(photoMapper.getPhotoByClassification(userId, classification));
    }

    @Override
    public HttpResult uploadFile(Long userId, MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("fileName：{}", fileName);
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        log.info("prefix：{}", prefix);
        // 用uuid作为文件名，防止生成的临时文件重复
        File excelFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
        // MultipartFile to File
        file.transferTo(excelFile);

        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        String etag = AliyunOSSClientUtil.uploadObject2OSS(ossClient, excelFile, OSSClientConstants.BACKET_NAME, OSSClientConstants.FOLDER);
        // 记录数据库
        String url = AliyunOSSClientUtil.getUrl(ossClient, excelFile.getName());
        Photo photo = Photo.builder().photoName(fileName)
                .path(url)
                .createTime(new Date())
                .additionalInfomation("")
                .burnAfterReading(0)
                .classification("0")
                .likeNumber(0)
                .resolutionRatio(photoRatioFromServe(AliyunOSSClientUtil.getUrl(ossClient, OSSClientConstants.FOLDER + excelFile.getName())))
                .tag("0")
                .userId(userId)
                .numberOfShare(0)
                .commentsNumber(0)
                .communicationStep(0)
                .size(photoSize(file))
                .build();
        photoMapper.insert(photo);
        //程序结束时，删除临时文件
        deleteFile(excelFile);
        log.info("etag；{}", etag);
        return HttpResult.ok(photo);
    }

    /**
     * 图片的分辨率
     */
    public String photoRatio(MultipartFile file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            return width + "x" + height;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取图片分辨率（服务器）
     *
     * @param photoUrl
     * @return
     * @throws IOException
     */
    public String photoRatioFromServe(String photoUrl) throws IOException {
        URL url = new URL(photoUrl);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        BufferedImage image = ImageIO.read(connection.getInputStream());
        int srcWidth = image .getWidth();      // 源图宽度
        int srcHeight = image .getHeight();    // 源图高度
        return srcHeight + "x" + srcWidth;
    }


    /**
     * 图片大小，以kb为单位
     */
    public Double photoSize(MultipartFile file) {

        long size = file.getSize();

        return size / KB;
    }

    /**
     * 文件删除
     *
     * @param files 文件
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

//    /**
//     * MultiPartFile转File
//     *
//     * @param file
//     * @param dir
//     * @throws IOException
//     */
//    public void write(MultipartFile file, Path dir) throws IOException {
//        Path filepath = Paths.get(dir.toString(), file.getOriginalFilename();
//
//        try (OutputStream os = Files.newOutputStream(filepath)) {
//            os.write(file.getBytes());
//        }
//    }
}
