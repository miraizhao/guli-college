package com.riying.commonutils;


/**
 * @author: Mirai Zhao
 * @create: 2021-07-26  16:15
 * @Description: 定义数据返回状态码
 * <p>
 *                  成功 20000
 * <p>
 *                  失败 20001
 */
public interface ResultCode {
    /**
     * 成功
     */
     Integer SUCCESS = 20000;
    /**
     * 失败
     */
     Integer ERROR = 20001;
}
