package com.riying.eduservice.service;

import com.riying.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.riying.eduservice.entity.vo.CourseInfoVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-31
 */
public interface EduCourseService extends IService<EduCourse> {
    String saveCourseInfo(CourseInfoVO courseInfoVO);
}
