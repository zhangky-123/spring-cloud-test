package com.chw.test.controller;


import com.chw.test.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author CarlBryant
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TestFeign testFeign;

    @GetMapping("/testFirst")
    public String testFirst(){
        System.out.println("请求调用消费者");
        return testFeign.testOne();
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World,I am consumer!";
    }


}
