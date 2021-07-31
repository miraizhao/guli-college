package com.riying.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riying.baseservice.myexception.GuliException;
import com.riying.eduservice.entity.EduCourse;
import com.riying.eduservice.entity.EduCourseDescription;
import com.riying.eduservice.entity.EduVideo;
import com.riying.eduservice.entity.vo.CourseInfoVO;
import com.riying.eduservice.mapper.EduCourseMapper;
import com.riying.eduservice.service.EduCourseDescriptionService;
import com.riying.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-31
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    EduCourseDescriptionService descriptionService;

    @Autowired
    public void setDescriptionService(EduCourseDescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @Override
    public String saveCourseInfo(CourseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVO, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new GuliException(20001, "添加课程信息失败");
        }
        String id = eduCourse.getId();
        courseDescription.setDescription(courseInfoVO.getDescription());
        courseDescription.setId(id);
        descriptionService.save(courseDescription);
        return id;

    }

    @Override
    public CourseInfoVO getCourseInfo(String courseId) {
        CourseInfoVO courseInfoVO=new CourseInfoVO();
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription courseDescription = descriptionService.getById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfoVO);
        courseInfoVO.setDescription(courseDescription.getDescription());
        return courseInfoVO;
    }

    @Override
    public void updateCourseInfo(CourseInfoVO courseInfoVO) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i==0){
            throw new GuliException(20001,"修改失败");
        }
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVO.getId());
        eduCourseDescription.setDescription(courseInfoVO.getDescription());
        descriptionService.updateById(eduCourseDescription);

    }
}
