package com.riying.eduservice.controller;


import com.riying.commonutils.R;
import com.riying.eduservice.entity.vo.CourseInfoVO;
import com.riying.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/eduservice/educourse")
@CrossOrigin
@Api(tags = {"课程管理"})
public class EduCourseController {
    private EduCourseService courseService;

    @Autowired
    public void setCourseService(EduCourseService courseService) {
        this.courseService = courseService;
    }
    @ApiOperation("新增课程信息")
    @PostMapping("saveCourseInfo")
    public R saveCourseInfo(@RequestBody CourseInfoVO courseInfoVO){
        String id=courseService.saveCourseInfo(courseInfoVO);
        return R.ok().data("courseId",id);
    }
}

