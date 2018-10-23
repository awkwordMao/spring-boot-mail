package com.jl.springbootmail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    @Test
    public void sendTemplateMailTest() throws Exception{
        Context context = new Context();
        //设置email.html中的 id 参数
        context.setVariable("id", 007);

        String emailTemplate = templateEngine.process("email", context);

        mailService.sendHtmlMail("2624824150@qq.com", "模板邮件", emailTemplate);
    }

    @Test
    public void sendInlineResourceMailTest() throws Exception {
        String imgPath = "C:/Users/007/Pictures/Camera Roll/timg.jpg";
        String rscId = "neo001";
        String content = "<html>\n<body>\n" +
                "这是有图片的邮件:<img src=\'cid:" + rscId
                + "\'></img>" +
                "</body></html>";
        mailService.sendInlineResourceMail("2624824150@qq.com", "图片邮件", content, imgPath, rscId);
    }

    @Test
    public void sendAttachmentMailTest() throws Exception {
        String filePath = "C:/Users/007/Desktop/zl/1/骚话.docx";
        mailService.sendAttachmentMail("2624824150@qq.com", "第一封附件邮件", "带附件的邮件", filePath);
    }

    @Test
    public void sendHtmlMailTest() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>这是一封html邮件</h3>" +
                "</body>" +
                "</html>";

        mailService.sendHtmlMail("2624824150@qq.com", "第一封邮件",content);
    }

    @Test
    public void sendSimpleMailTest() throws Exception {
        mailService.sendSimpleMail("2624824150@qq.com", "第一封邮件", "你好，这是第一封邮件");
    }

    @Test
    public void test1(){
        System.out.println("hello");
    }

}