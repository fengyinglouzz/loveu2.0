package com.zxzyyyy.loveu.service;

import com.zxzyyyy.loveu.dto.EmailDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;

import static com.zxzyyyy.loveu.constant.mailConstants.SMTPCODE;
import static com.zxzyyyy.loveu.constant.mailConstants.USERNAME;

@Service
public class MailService {
    public void createTs(EmailDto emailDto) throws IOException, MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");

        //使用JavaMail发送邮件的5个步骤

        //1、创建session，并开启debug模式，这样就可以查看到程序发送Email的运行状态
        Session session = Session.getInstance(prop);
        session.setDebug(true);

        //2、通过session得到transport对象
        Transport ts = session.getTransport();

        //3、连上邮件服务器
        ts.connect("smtp.qq.com", USERNAME, SMTPCODE);

        //4、创建邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailDto.getSender()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getReceiver()));
        message.setSubject(emailDto.getSubject());

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(emailDto.getContent(), "text/html;charset=UTF-8");

        if(emailDto.getFile() != null) {
            //创建邮件附件
            File f = transfer(emailDto.getFile());
            MimeBodyPart attach = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(f);
            DataHandler dh = new DataHandler(fds);
            attach.setDataHandler(dh);
            attach.setFileName(MimeUtility.encodeText(fds.getName()));

            //创建容器描述数据关系
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(text);
            mp.addBodyPart(attach);
            mp.setSubType("mixed");
            message.setContent(mp);
            message.saveChanges();

            //5、发送邮件，并删除临时文件
            ts.sendMessage(message, message.getAllRecipients());
            File del = new File(f.toURI());
            del.delete();
            ts.close();
        } else {
            //创建容器描述数据关系
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(text);
            mp.setSubType("mixed");
            message.setContent(mp);
            message.saveChanges();

            ts.sendMessage(message, message.getAllRecipients());
        }
    }

    private File transfer(MultipartFile file) throws IOException {
        InputStream ins = file.getInputStream();
        File f = new File(Objects.requireNonNull(file.getOriginalFilename()));
        inputStreamToFile(ins, f);
        return f;
    }

    private void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while (-1 != (bytesRead = ins.read(buffer, 0, 8192))) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
