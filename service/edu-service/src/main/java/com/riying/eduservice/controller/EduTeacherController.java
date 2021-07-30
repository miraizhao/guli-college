package com.riying.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riying.commonutils.R;
import com.riying.eduservice.entity.EduTeacher;
import com.riying.eduservice.entity.vo.TeacherQueryVO;
import com.riying.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-26
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    private EduTeacherService teacherService;

    /**
     * 注入service
     */
    @Autowired
    public void setTeacherService(EduTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * test查询讲师表中的所有数据
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 逻辑删除讲师
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("delete/{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师id", readOnly = true)
                           @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师的方法
     *
     * @param current 当前页
     * @param limit   每页的量
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        Page<EduTeacher> page = teacherService.page(teacherPage, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    //    @GetMapping("pageTeacherCondition/{current}/{limit}")
//    public R pageTeacherCondition(@PathVariable long current,
//                                  @PathVariable long limit,
//                                  TeacherQueryVO teacherQueryVO) {
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQueryVO teacherQueryVO) {
//        创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
//        构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
//        条件组合查询
//        动态sql 判断是否为空
        String begin = teacherQueryVO.getBegin();
        String end = teacherQueryVO.getEnd();
        Integer level = teacherQueryVO.getLevel();
        String name = teacherQueryVO.getName();
        queryWrapper.orderByDesc("gmt_create");
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
//        调用方法实现分页
        Page<EduTeacher> page = teacherService.page(teacherPage, queryWrapper);
//        总记录数
//        System.out.println(page.equals(teacherPage));
        long total = teacherPage.getTotal();
//        数据list集合
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 新增教师方法
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable String id) {
        EduTeacher byId = teacherService.getById(id);
        return R.ok().data("teacher", byId);
    }

    @PostMapping("updateTeacher")
    public R updateTeacherBy(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

