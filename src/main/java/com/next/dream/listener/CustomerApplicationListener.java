package com.next.dream.listener;

import com.next.dream.init.CustomerApplicationContextInitlater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class CustomerApplicationListener implements ApplicationListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApplicationContextInitlater.class);


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        LOGGER.info("start listener Application!");
        if(applicationEvent instanceof ApplicationStartingEvent){
            LOGGER.info("touch ApplicationStaring Event");
        }
        if(applicationEvent instanceof ApplicationPreparedEvent){
            LOGGER.info("touch Application Prepared Event");
        }
        if(applicationEvent instanceof ApplicationFailedEvent){
            LOGGER.info("touch Application Failed Event");
        }
        if(applicationEvent instanceof ApplicationReadyEvent){
            LOGGER.info("touch Application Ready Event");
        }

    }
}
