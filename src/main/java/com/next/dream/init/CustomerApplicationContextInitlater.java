package com.next.dream.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomerApplicationContextInitlater implements ApplicationContextInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApplicationContextInitlater.class);

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //TODO 定义自己的初始化容器
        LOGGER.info("this is my Application Context Initlater !");

    }
}
