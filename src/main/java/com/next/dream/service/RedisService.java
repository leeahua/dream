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


    public void set(String key,  Object obj,long timeout, TimeUnit outTime){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, obj, timeout,outTime);
        log.info("key:{},添加缓存成功",key);
    }

    public Object get(String key){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public  void remove(String key){
        if(redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            log.info("key:{},已从缓存中删除！"+key);
        }
    }

}
