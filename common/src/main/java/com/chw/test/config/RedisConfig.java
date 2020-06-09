package com.chw.test.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * redis连接工厂
     */
    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
     * 配置自定义redisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        //使用自定义FastJsonRedisSerializer序列化和反序列化redis的value值
        template.setValueSerializer(fastJsonRedisSerializer());
        template.setKeySerializer(fastJsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * FastJson序列化Bean
     */
    @Bean
    public FastJsonRedisSerializer<?> fastJsonRedisSerializer() {
        return new FastJsonRedisSerializer<>(Object.class);
    }


}
