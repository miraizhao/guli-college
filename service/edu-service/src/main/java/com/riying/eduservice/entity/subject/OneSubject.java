package com.riying.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-30  16:08
 * @Description:
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children=new ArrayList<>();
}
