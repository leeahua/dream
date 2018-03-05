package com.next.dream.annotation;

import java.lang.annotation.*;

/**
 * 描述：〈登陆注解〉
 *
 * @author liyaohua
 * create on 2018/3/5
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME) //注解会在字节码中存在，可反射获取
@Target({ElementType.METHOD}) //定义注解的作用目标**作用范围字段
@Documented
public @interface LoginAnnotation {
    String name() default "";
}
