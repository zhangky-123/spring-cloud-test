package com.chw.test.controller;


import com.chw.test.dto.ApiResponseDTO;
import com.chw.test.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author CarlBryant
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/api/feign")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World,I am provider!";
    }

    @GetMapping("/test")
    public ApiResponseDTO test(){
        return new ApiResponseDTO(userService.list());
    }
}
