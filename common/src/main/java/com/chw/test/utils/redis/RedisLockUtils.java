package com.chw.test.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RedisLockUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Long SUCCESS = 1L;

    /**
     * 获取分布式锁
     * @param lockKey 锁
     * @param value 获得锁的人的标识
     * @param expireTime 过期时间，单位秒
     * @return true
     */
    public boolean getLock(String lockKey, String value, int expireTime){
        String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, expireTime);
        if(SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param value 获得锁的人的标识
     * @return true
     */
    public boolean releaseLock(String lockKey, String value){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey),value);
        if(SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
