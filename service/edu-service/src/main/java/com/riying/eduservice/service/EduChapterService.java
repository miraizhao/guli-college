package com.riying.eduservice.service;

import com.riying.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.riying.eduservice.entity.chapter.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-31
 */
public interface EduChapterService extends IService<EduChapter> {


    List<ChapterVO> getChapterVideoByCourseId(String courseId);
}
