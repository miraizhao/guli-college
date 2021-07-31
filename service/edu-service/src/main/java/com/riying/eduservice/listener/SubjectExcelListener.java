package com.riying.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riying.baseservice.myexception.GuliException;
import com.riying.eduservice.entity.EduSubject;
import com.riying.eduservice.entity.excel.SubjectData;
import com.riying.eduservice.service.EduSubjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-30  11:23
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService subjectService;
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
//        一行一行读取，每次读取两个值，第一个值为一级分类，第二个值为二级分类
//        判断以及分类是否重复
        EduSubject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject==null){//没有相同一级分类
            oneSubject=new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }
        //获取以及分类id
        String pid=oneSubject.getId();
        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(twoSubject == null){
            twoSubject=new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }
    }

    /**
     * 判断一级分类能不能重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name).eq("parent_id","0");
        return  subjectService.getOne(wrapper);

    }
    /**
     * 判断二级分类能不能重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name).eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
