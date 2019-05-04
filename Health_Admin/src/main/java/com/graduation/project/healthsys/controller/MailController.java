package com.graduation.project.healthsys.controller;

import com.graduation.project.healthsys.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api")

public class MailController {

    @Autowired
    JavaMailSender mailSender;

    @RequestMapping(value ="/mail/{email}/{content}", method = RequestMethod.GET)
    public Object sendEmail(@PathVariable("email") String email,@PathVariable("content") String content) {
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
