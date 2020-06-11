package com.chw.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients({"com.chw.test.feign"})
@MapperScan("com.chw.test.mapper")
public class FirstConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(FirstConsumerApp.class, args);
    }
}
