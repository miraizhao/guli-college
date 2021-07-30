package com.riying.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-29  15:22
 * @Description:
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//默认不加载数据库配置
@ComponentScan(basePackages = "com.riying")
public class OssApplication8002 {
    public static void main(String[] args) {
            SpringApplication.run(OssApplication8002.class,args);
        }
}
