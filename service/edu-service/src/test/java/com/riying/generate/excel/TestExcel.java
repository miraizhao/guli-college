package com.riying.generate.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-30  09:50
 * @Description:
 */
public class TestExcel {
    public static void main(String[] args) {
        //    实现excel读取操作
        String filename="D:\\write.xlsx";
        EasyExcel.read(filename,Demodata.class,new ExcelListener()).sheet().doRead();
    }




//    public static void main(String[] args) {
//        //实现excel写的操作
////        1.设置写入文件夹地址和excel文件名称
//        String filename="D:\\write.xlsx";
////        2.调用esayexcel里面的方法实现
////                    （文件路径名称，实体类）          sheet的名称                构建
//        EasyExcel.write(filename,Demodata.class).sheet("学生列表").doWrite(getData());
//    }
    private static List<Demodata> getData(){
        List<Demodata> demodata=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Demodata data=new Demodata();
            data.setSname("lucy"+i);
            data.setSno(i);
            demodata.add(data);
        }
        return demodata;
    }
}
