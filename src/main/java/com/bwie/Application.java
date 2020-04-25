package com.bwie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/21 14:43
 */
@SpringBootApplication
@EnableScheduling //启动定时任务的注解
@MapperScan("com.bwie.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
