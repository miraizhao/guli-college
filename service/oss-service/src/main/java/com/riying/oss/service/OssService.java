package com.riying.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-29  15:52
 * @Description:
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
