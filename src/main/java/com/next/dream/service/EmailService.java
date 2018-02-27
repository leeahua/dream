package com.next.dream.service;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

/**
 * 描述：邮箱服务
 *
 * @author liyaohua
 * @create 2018/2/27
 * @since 1.0.0
 */
public interface EmailService {
    /**
     * 发送文本邮件
     * @param tagEmail 目标邮箱
     * @param content 发送内容
     * @return
     * @author liyaohua
     * Created On 2018/2/27 上午11:34
     */
    void sendTextMail(String tagEmail,String subject,String content) throws EmailException;

    void sendHtmlMail(String tagEmail,String subject,String content) throws EmailException;

    String getRegisterUrlLink();
}
