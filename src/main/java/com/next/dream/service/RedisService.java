package com.next.dream.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 描述：〈redis〉
 *
 * @author liyaohua
 * create on 2018/3/8
 * @version 1.0
 */
@Component
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public void set(String key,  Object obj, TimeUnit outTime){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, obj, 10, TimeUnit.SECONDS);
    }

    public Object get(String key){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

}
