package com.next.dream.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 定制自己的CommandLineRunner
 * @Author liyaohu
 * @Date 20180111
 * */
@Component
@Order(1)
public class CustomerCommandLineRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("this is customer Runner");
        }
    }
}
