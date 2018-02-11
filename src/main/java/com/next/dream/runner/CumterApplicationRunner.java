package com.next.dream.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CumterApplicationRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CumterApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("this is customer Applicaton Runner");
        }
    }
}
