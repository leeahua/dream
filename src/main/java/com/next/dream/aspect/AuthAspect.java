package com.next.dream.aspect;

import com.next.dream.dto.BaseRespDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.utils.JsonUtil;
import com.next.dream.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 描述：〈身份拦截〉
 * 对于需要登陆拦截的地方进行拦截
 * @author liyaohua
 * create on 2018/3/1
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class AuthAspect {

    /**
     * @Description: <br>
     *  定义拦截器规则
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/3/1 下午5:29
     */
    @Pointcut("execution(* com.next.dream.controller.api.ArticleController.*(..)) && @annotation(com.next.dream.annotation.LoginAnnotation)")
    public void authorControllerPointcut(){}

    /**
     * 拦截器具体实现
     * @param proceedingJoinPoint
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("authorControllerPointcut()")
    public Object Interceptor(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        //获取被拦截的方法
        Method method = methodSignature.getMethod();
        //获取被拦截的方法的名字
        String methodName = method.getName();
        Set<Object> params = new LinkedHashSet<>();
        log.info("请求开始，请求方法：{}",methodName);
        Object result = null;
        Object[] args = proceedingJoinPoint.getArgs();
        for(Object obj : args){
            if(obj instanceof Map<?,?>){
                Map<String,Object> map = (Map<String,Object>)obj;
                params.add(map);
            }else if(obj instanceof BaseRespDto){
                BaseRespDto baseRespDto = (BaseRespDto) obj;
                if(baseRespDto.getToken()==null){
                    result = ResultVOUtil.failed(ResultEnum.USER_UNLOGIN_ERROR);
                    break;
                }

            }else{
                params.add(args);
            }
        }

        if(result==null){
            try {
                result = proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                log.error("服务器部错误",e);
                result = ResultVOUtil.failed(ResultEnum.SERVICE_EXCEPTION);
            }

        }
        log.info("请求结果：{}", JsonUtil.toJson(result));
        return result;
    }


}
