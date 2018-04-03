package com.next.dream.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 描述：〈日志跟踪〉
 *
 * @author liyaohua
 * create on 2018/3/13
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * @Description: <br>
     *  定义拦截器规则
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/3/1 下午5:29
     */
    @Pointcut("execution(* com.next.dream.controller.api.ArticleApiController.*(..)) && @annotation(com.next.dream.annotation.LoginAnnotation)")
    public void authorControllerPointcut(){}

    @Before("authorControllerPointcut()")
    public Object interceptor(JoinPoint joinPoint){

        return null;
    }

}
