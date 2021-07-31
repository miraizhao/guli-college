package com.riying.baseservice.exceptionhandler;


import com.riying.baseservice.myexception.GuliException;
import com.riying.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-27  10:11
 * @Description:
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @ExceptionHandler 指定什么异常执行此方法
     * @ResponseBody 返回JSON数据
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("发生了异常");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("发生了ArithmeticException异常");
    }
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
