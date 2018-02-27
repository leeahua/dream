package com.next.dream.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 描述：〈邮件配置〉
 *
 * @author liyaohua
 * create on 2018/2/27
 * @version 1.0
 */
@Component("emailconfig")
@ConfigurationProperties(prefix = "email")
@PropertySource("classpath:/application.yml")
@Data
public class EmailProperConfig {
    private String host;
    private int port ;
    private String username;
    //客户端授权码
    private String password;
    private String registerActivicateLinkUrl;
}
