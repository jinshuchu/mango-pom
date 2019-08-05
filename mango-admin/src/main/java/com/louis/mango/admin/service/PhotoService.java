package com.louis.mango.admin.service;

import com.louis.mango.core.http.HttpResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-31
 */
public interface PhotoService {

    HttpResult save(MultipartFile file, Long userId);

    HttpResult getPhotoByClassification(Long userId, String classification);

    HttpResult uploadFile(Long userId, MultipartFile file) throws IOException;
}
