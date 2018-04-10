package com.next.dream.config;

import com.next.dream.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileProperConfigTest {

    @Autowired
    private FileProperConfig fileProperConfig;

    @Test
    public void testPro(){
        log.info(JsonUtil.toJson(fileProperConfig));
    }

}