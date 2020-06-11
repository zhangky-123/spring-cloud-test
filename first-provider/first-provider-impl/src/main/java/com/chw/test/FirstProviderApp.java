package com.chw.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.chw.test.mapper")
public class FirstProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(FirstProviderApp.class, args);
    }
}
