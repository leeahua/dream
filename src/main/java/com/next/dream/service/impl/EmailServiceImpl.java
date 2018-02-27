package com.next.dream.service.impl;

import com.next.dream.config.EmailProperConfig;
import com.next.dream.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述：〈邮件服务实现〉
 *
 * @author liyaohua
 * create on 2018/2/27
 * @version 1.0
 */
@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private EmailProperConfig emailProperConfig;

    @Override
    public void sendTextMail(String tagEmail,String subject ,String content) throws EmailException {
        SimpleEmail mail = new SimpleEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(emailProperConfig.getPort());
        mail.setHostName(emailProperConfig.getHost());
        // 设置密码验证器
        mail.setAuthentication(emailProperConfig.getUsername(), emailProperConfig.getPassword());
        // 设置邮件发送者
        mail.setFrom(emailProperConfig.getUsername());
        // 设置邮件接收者
        mail.addTo(tagEmail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject(subject);
        // 设置邮件内容
        mail.setMsg(content);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    @Override
    public void sendHtmlMail(String tagEmail,String subject, String content) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(emailProperConfig.getPort());
        mail.setHostName(emailProperConfig.getHost());
        // 设置密码验证器
        mail.setAuthentication(emailProperConfig.getUsername(), emailProperConfig.getPassword());
        // 设置邮件发送者
        mail.setFrom(emailProperConfig.getUsername());
        // 设置邮件接收者
        mail.addTo(tagEmail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject(subject);
        // 设置邮件内容
        mail.setHtmlMsg(content);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    @Override
    public String getRegisterUrlLink() {
        return emailProperConfig.getRegisterActivicateLinkUrl();
    }


}
