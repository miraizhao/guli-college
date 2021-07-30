package com.riying.generate.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-30  10:43
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener<Demodata> {
//    一行一行读取excel内容
    @Override
    public void invoke(Demodata demodata, AnalysisContext analysisContext) {
        System.out.println(demodata);
    }
//    读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.out.printf("表头", headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
