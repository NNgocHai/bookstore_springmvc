package com.bookstore.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.util.Properties;
@Component
public class SendingEmail {
    @Autowired
    ServletContext context;

    @Autowired
    JavaMailSender mailSender;


    public void SendingEmail1(String userEmail,String name,String code)
    {
        final String email="cuahangsachso6@gmail.com";
        final String pword="Tu18110387";
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(userEmail);
            helper.setReplyTo(email);
            helper.setSubject("User Email Verification");
            String body="Use this code: "+code+" to finished registation";
            helper.setText(body, true);
            mailSender.send(message);
        }
        catch(Exception ex){
            System.out.println("SendingEmail..."+ex);
        }
    }

    /*public void sendMail(){
        String email="nthanhtu243@gmail.com";
        String pword="Nttgmaclrte243";
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        Session session=Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,pword);
            }
        });
        try
        {
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(userEmail));
            message.setSubject("Study in Email Verification Link");
            message.setText("Verifiction Links...");
            message.setText("Your Verification Link ::"+"http://localhost:8082/web666_war_exploded/activeAccount?key1="+userEmail+"&key2="+myHash);
            Transport.send(message);
        }
        catch (Exception e)
        {
            System.out.println("SendingEmail..."+e);
        }
    }*/
}
