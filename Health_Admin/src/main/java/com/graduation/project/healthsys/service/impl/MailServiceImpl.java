package com.graduation.project.healthsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.internet.MimeMessage;

@Service("MailServiceImpl")
public class MailServiceImpl {

    @Autowired
    JavaMailSender mailSender;

    public Object SendEmail(@PathVariable("email") String email, @PathVariable("content") String content) {
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("272262983@qq.com");
            message.setTo(email);
            message.setSubject("体检结果");
            message.setText(content);
            this.mailSender.send(mimeMessage);
            return "sucesss";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
}
