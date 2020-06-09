package com.chw.test.config;


import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * spring-security5.0开启共享session无需额外配置，一个注解搞定
 * maxInactiveIntervalInSeconds指定session的有效期，单位：秒
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1200)
public class HttpSessionConfig {

}
