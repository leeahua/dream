package com.next.dream.service.impl;

import com.next.dream.service.EmailService;
import com.next.dream.utils.SendEmailUtil;
import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendTextMail() {
        try {
            emailService.sendTextMail("396317263@qq.com","测试","heool");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendHtmlMail() {
       /* try {
            String username = "我是大神";
            String urlLink = "https://www.baidu.com";
            String content = SendEmailUtil.emaliTemplate().replace("$username$",username).replace("$urllink$",urlLink);
            //emailService.sendHtmlMail("396317263@qq.com","html页面", content);
        } catch (EmailException e) {
            e.printStackTrace();
        }*/
    }
}