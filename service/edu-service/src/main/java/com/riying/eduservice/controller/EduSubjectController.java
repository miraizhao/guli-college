package com.riying.eduservice.controller;


import com.riying.commonutils.R;
import com.riying.eduservice.entity.subject.OneSubject;
import com.riying.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-30
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    private EduSubjectService subjectService;
    @Autowired
    public void setSubjectService(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 添加课程分类 获取上传过来的文件，把内容读取出来
     * @param file
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list=subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

