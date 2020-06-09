package com.chw.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulStartApplication.class, args);
    }
}
