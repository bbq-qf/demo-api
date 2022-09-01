package com.bjpowernode.demopro2;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.bjpowernode.demopro2.dao")
public class DemoPro2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoPro2Application.class, args);
    }

}
