package com.zxzyyyy.loveu.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.zxzyyyy.loveu.dto.EmailDto;
import com.zxzyyyy.loveu.response.Data;
import com.zxzyyyy.loveu.response.ResultMap;
import com.zxzyyyy.loveu.service.MailService;
import com.zxzyyyy.loveu.util.GenerateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    private final MailService mailService;
    private final GenerateCode generateCode;


    @Autowired
    public MailController(MailService mailService, GenerateCode generateCode) {
        this.mailService = mailService;
        this.generateCode = generateCode;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResultMap send() throws MessagingException, IOException, javax.mail.MessagingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("这是标题");
        emailDto.setContent("这是内容");
        emailDto.setReceiver("623385757@qq.com");
        mailService.createTs(emailDto);
        return new ResultMap().success().message("发送成功，请查看邮箱");
    }

    @RequestMapping(value = "/{emailAddress}", method = RequestMethod.POST)
    public ResultMap signup(@PathVariable String emailAddress) throws MessagingException, IOException, javax.mail.MessagingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("恋爱记注册邮件");
        emailDto.setReceiver(emailAddress);
        String code = generateCode.getRandNum(6);
        emailDto.setContent("您的验证码为： " + code + "\n" + "来开启恋爱之旅吧！");
        mailService.createTs(emailDto);
        return new ResultMap().success().message("").data(new Data().addObj("code", code));
    }
}
