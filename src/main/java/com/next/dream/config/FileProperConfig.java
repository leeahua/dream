package com.next.dream.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 描述：〈文件配置〉
 *
 * @author liyaohua
 * create on 2018/4/10
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "upload")
@PropertySource("classpath:/application-config.yml")
@Data
@EqualsAndHashCode
public class FileProperConfig {
    private String path;
    private String format;
}
