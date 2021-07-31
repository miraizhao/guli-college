package com.riying.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-31  14:08
 * @Description:
 */
@Data
public class ChapterVO {
    @ApiModelProperty(value = "章节ID")
    private String id;
    @ApiModelProperty(value = "章节名称")
    private String title;
    private List<VideoVO> children = new ArrayList<>();
}
