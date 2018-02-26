package com.next.dream.utils;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * 描述：〈邮件发送〉
 * 需要开启POP3/SMTP服务 客户端授权码作为密码
 * @author liyaohua
 * create on 2018/2/24
 * @version 1.0
 */
public class SendEmailUtil {


    //private String host = "smtp.126.com";
    private static String host = "smtp.163.com";
    private static int port = 25;
    private static String userName = "leeahuamsg@163.com";
    //客户端授权码
    private static  String password = "1q2w3e4r1q2w3e4r";
    private static String to = "396317263@qq.com";


    /**
     * 发送文本邮件
     *
     * @throws Exception
     */
    public static void sendTextMail(String tagMail,String code) throws Exception
    {
        SimpleEmail mail = new SimpleEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        mail.addTo(tagMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("注册激活码");
        // 设置邮件内容
        mail.setMsg("亲，您的注册激活码："+code);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    /**
     * 发送Html邮件
     *
     * @throws Exception
     */
    public static void sendHtmlMail(String tagMail) throws Exception
    {
        HtmlEmail mail = new HtmlEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        mail.addTo(tagMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("Test Email");
        // 设置邮件内容
        mail.setHtmlMsg(
                "<html><body><img src='http://localhost:3000/register/activate?username=liyaohua&activateCode=973966'/><div>点击激活.</div></body></html>");
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    /**
     * 发送内嵌图片邮件
     *
     * @throws Exception
     */
    public static void sendImageMail(String tagMail) throws Exception
    {
        HtmlEmail mail = new HtmlEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        mail.addTo(tagMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("Test Email");
        mail.embed(new File("1_jianggujin.jpg"), "image");
        // 设置邮件内容
        String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
        mail.setHtmlMsg(htmlText);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    /**
     * 发送附件邮件
     *
     * @throws Exception
     */
    public static void sendAttachmentMail(String tagMail) throws Exception
    {
        MultiPartEmail mail = new MultiPartEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        mail.addTo(tagMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("Test Email");

        mail.setMsg("this is a Attachment email.this email has a attachment!");
        // 创建附件
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("1_jianggujin.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName("1_jianggujin.jpg");
        mail.attach(attachment);

        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    /**
     * 发送内嵌图片和附件邮件
     *
     * @throws Exception
     */
    public static void sendImageAndAttachmentMail(String tagMail) throws Exception
    {
        HtmlEmail mail = new HtmlEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        mail.addTo(to);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("Test Email");
        mail.embed(new File("1_jianggujin.jpg"), "image");
        // 设置邮件内容
        String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
        mail.setHtmlMsg(htmlText);
        // 创建附件
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("1_jianggujin.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName("1_jianggujin.jpg");
        mail.attach(attachment);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }
}
