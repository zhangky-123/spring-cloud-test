package com.chw.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("first-provider")
public interface TestFeign {

    @GetMapping("/testOne")
    String testOne();
}
