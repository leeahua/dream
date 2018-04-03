package com.next.dream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 描述：〈springsession配置〉
 *
 * @author liyaohua
 * create on 2018/3/29
 * @version 1.0
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 180)
public class HttpSessionConfig {

}
