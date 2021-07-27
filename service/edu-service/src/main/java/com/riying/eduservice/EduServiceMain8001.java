package com.riying.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-26  10:40
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.riying")
public class EduServiceMain8001 {
    public static void main(String[] args) {
            SpringApplication.run(EduServiceMain8001.class,args);
        }
}
