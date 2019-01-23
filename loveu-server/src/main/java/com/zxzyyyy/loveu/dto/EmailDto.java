package com.zxzyyyy.loveu.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.zxzyyyy.loveu.constant.mailConstants.USERNAME;

@Data
public class EmailDto {
    //正文
    private String content;
    //标题
    private String subject;
    //发件人
    private final String sender = USERNAME + "@qq.com";
    //收件人
    private String receiver;
    private MultipartFile file;
}
