package com.cesaba.siriusmobliemain;


import com.cesaba.siriusmobliemain.dto.VerifyCode;
import com.cesaba.siriusmobliemain.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.thymeleaf.context.Context;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Resource
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;


    @Test
    public void test() throws Exception{
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendMail("2392158794@qq.com", "测试邮件",emailContent);
    }
}
