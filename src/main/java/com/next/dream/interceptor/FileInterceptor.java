package com.next.dream.interceptor;

import com.next.dream.exeption.UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * 描述：〈文件类型过滤拦截器〉
 *
 * @author liyaohua
 * create on 2018/4/10
 * @version 1.0
 */
@Slf4j
public class FileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("文件拦截器启动...当前session：{}",request.getSession()==null?null:request.getSession().getId());
        MultipartResolver multipartResolver = new CommonsMultipartResolver();
        if(multipartResolver.isMultipart(request)){
            log.info("请求中含有附件，开始校验...");
            //处理带附件的请求
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();
            Iterator<String> iterator = fileMap.keySet().iterator();
            while(iterator.hasNext()){
                MultipartFile multipartFile = multipartRequest.getFile(iterator.next());
                String fileOriginalName = multipartFile.getOriginalFilename();
                if(!checkFileSuffix(fileOriginalName)){
                    throw new UploadException("文件格式不支持");
                }
            }
        }else{

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("postHandle");
    }

    private boolean checkFileSuffix(String fileName){
        String allowFileSuffix = "jpg,jpeg,gif,png,ico,bmp";
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        if(!allowFileSuffix.contains(suffix)){
            log.info("文件名称：{},不符合上传文件格式要求，不予接受",fileName);
            return false;
        }
        return true;
    }
}
