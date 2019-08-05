package com.louis.mango.admin.controller;

import com.louis.mango.admin.service.PhotoService;
import com.louis.mango.admin.util.AliyunOSSClientUtil;
import com.louis.mango.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-08-01
 */

@RestController
@RequestMapping(value = "photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    /**
     * 照片分类
     *
     * @param userId         用户id
     * @param classification 分类
     * @return
     */
    @GetMapping(value = "/classification")
    public HttpResult getPhotoByClassification(Long userId, String classification) {

        return photoService.getPhotoByClassification(userId, classification);
    }

    /**
     * 文件上传
     *
     * @param file   文件
     * @param userId 用户id
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public HttpResult uploadFile(@RequestParam(value = "file") MultipartFile file, Long userId) throws IOException {

        return photoService.uploadFile(userId, file);
    }

    /**
     * 上传进度
     *
     * @param request
     * @return
     */
    public int getUploadPercent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int percent = session.getAttribute("upload_percent") == null ? 0 : (int) session.getAttribute("upload_percent");
        return percent;
    }

    /**
     * 重置进度
     *
     * @param request
     */
    public void resetPercent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("upload_percent", 0);
    }

    /**
     * 获得url链接
     *
     * @param key 文件夹名+文件名路径，如admin/avatar.png
     * @return
     */
    public String getUrl(String key) {
        return AliyunOSSClientUtil.getUrl(AliyunOSSClientUtil.getOSSClient(), key);
    }
}