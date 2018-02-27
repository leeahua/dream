package com.next.dream.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailProperConfigTest {

    @Autowired
    private EmailProperConfig emailProperConfig;

    @Test
    public void testValue(){
        System.out.println(emailProperConfig.getHost());
    }

}