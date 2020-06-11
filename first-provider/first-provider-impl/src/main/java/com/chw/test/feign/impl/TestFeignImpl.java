package com.chw.test.feign.impl;

import com.chw.test.feign.TestFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/feign")
public class TestFeignImpl implements TestFeign {

    @GetMapping("/testOne")
    @Override
    public String testOne() {
        System.out.println("请求到达生产者,开始睡眠");
        Random random = new Random();
        int i = random.nextInt(5);
        try {
            Thread.sleep((i+1)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡眠结束");
        return "Hello World";
    }
}
