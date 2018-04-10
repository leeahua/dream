package com.next.dream.config;

import com.next.dream.interceptor.FileInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 描述：〈拦截器注册〉
 *
 * @author liyaohua
 * create on 2018/4/10
 * @version 1.0
 */
@Configuration
public class AppInteceptorAdapter extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/**").excludePathPatterns("dream/api/user/login");
        super.addInterceptors(registry);
    }
}
