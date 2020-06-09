package com.chw.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${from:null}")
    private String from;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Config From "+from;
    }
}
