package com.riying.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riying.eduservice.entity.EduSubject;
import com.riying.eduservice.entity.excel.SubjectData;
import com.riying.eduservice.entity.subject.OneSubject;
import com.riying.eduservice.entity.subject.TwoSubject;
import com.riying.eduservice.listener.SubjectExcelListener;
import com.riying.eduservice.mapper.EduSubjectMapper;
import com.riying.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author mirai.zhao
 * @since 2021-07-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper qwOne=new QueryWrapper();
        qwOne.eq("parent_id","0");
        List<EduSubject> oneList = baseMapper.selectList(qwOne);
        QueryWrapper qwTwo=new QueryWrapper();
        qwTwo.ne("parent_id","0");
        List<EduSubject> twoList = baseMapper.selectList(qwTwo);
        List<OneSubject> finalList=new ArrayList<>();
        oneList.forEach(one->{
            OneSubject oneSubject=new OneSubject();
//            oneSubject.setId(one.getId());
//            oneSubject.setTitle(one.getTitle());
            BeanUtils.copyProperties(one,oneSubject);

            List<TwoSubject> twoFinalList=new ArrayList<>();
            twoList.forEach(two->{
                if(two.getParentId().equals(one.getId())){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(two,twoSubject);
                    twoFinalList.add(twoSubject);
                }
            });
            oneSubject.setChildren(twoFinalList);
            finalList.add(oneSubject);
        });
        return finalList;
    }
}
