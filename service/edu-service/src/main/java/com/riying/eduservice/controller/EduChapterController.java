package com.riying.eduservice.controller;


import com.riying.commonutils.R;
import com.riying.eduservice.entity.chapter.ChapterVO;
import com.riying.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/eduservice/educhapter")
@CrossOrigin
public class EduChapterController {
    private EduChapterService eduChapterService;

    @Autowired
    public void setEduChapterService(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVO> chapterVOS=eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",chapterVOS);
    }

}

