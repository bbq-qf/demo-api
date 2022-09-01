package com.bjpowernode.democon;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DemoConApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConApplication.class, args);
    }

}
