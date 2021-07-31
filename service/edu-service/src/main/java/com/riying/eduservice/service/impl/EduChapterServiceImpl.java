package com.riying.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riying.eduservice.entity.EduChapter;
import com.riying.eduservice.entity.EduVideo;
import com.riying.eduservice.entity.chapter.ChapterVO;
import com.riying.eduservice.entity.chapter.VideoVO;
import com.riying.eduservice.mapper.EduChapterMapper;
import com.riying.eduservice.service.EduChapterService;
import com.riying.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-31
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    private EduVideoService eduVideoService;

    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }

    @Override
    public List<ChapterVO> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterQueryWrapper);

        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(eduVideoQueryWrapper);
        List<ChapterVO> finalList=new ArrayList<>();
        eduChapters.forEach(eduChapter -> {
            ChapterVO chapterVO=new ChapterVO();
            BeanUtils.copyProperties(eduChapter,chapterVO);
            List<VideoVO> finalVideoList=new ArrayList<>();
            eduVideos.forEach(eduVideo -> {
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVO videoVO=new VideoVO();
                    BeanUtils.copyProperties(eduVideo,videoVO);
                    finalVideoList.add(videoVO);
                }
            });
            chapterVO.setChildren(finalVideoList);
            finalList.add(chapterVO);
        });
        return finalList;
    }
}
