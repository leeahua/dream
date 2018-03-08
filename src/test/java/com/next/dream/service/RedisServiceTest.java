package com.next.dream.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;


    @Test
    public void set() {
        /*

        */
        redisService.set("1", "bbb", 20, TimeUnit.SECONDS);
        System.out.println(redisService.get("1"));
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.setIfAbsent("1", "ccc");
        System.out.println(redisService.get("1"));
        Assert.assertNotNull(redisService.get("1"));
    }

    @Test
    public void get() {
        redisTemplate.delete("1");
        System.out.println(redisService.get("1"));
    }
}