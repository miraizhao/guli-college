package com.riying.eduservice.service;

import com.riying.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.riying.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-30
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
