package com.chw.test.feign.impl;

import com.chw.test.feign.TestFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestFeignImpl implements TestFeign {

    @GetMapping("/testOne")
    @Override
    public String testOne() {
        System.out.println("请求到达生产者,开始睡眠");
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡眠结束");
        return "Hello World";
    }
}
