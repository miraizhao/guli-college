package com.riying.generate.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-30  09:48
 * @Description:
 */
@Data
public class Demodata {
    @ExcelProperty(value = "学生编号",index=0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index=1)
    private String sname;
}
