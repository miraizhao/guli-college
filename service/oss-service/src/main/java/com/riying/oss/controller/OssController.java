package com.riying.oss.controller;

import com.riying.commonutils.R;
import com.riying.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-29  15:51
 * @Description:
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    private OssService ossService;
    @Autowired
    public void setOssService(OssService ossService) {
        this.ossService = ossService;
    }

    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
