package com.bookstore.email;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendingEmail {
    private String userEmail;
    private String name;
    private String code;
    public SendingEmail(String userEmail,String name,String code)
    {
        this.userEmail=userEmail;
        this.name=name;
        this.code=code;
        final String email="cuahangsachso6@gmail.com";
        final String pword="Tu18110387";



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
            message.setSubject("User Email Verification");
            String h="Dear "+name+",\n\n"
                    + "You have just registed your account succesfully. :: "+"http://localhost:8082/web666_war_exploded/web/login"
                    + "\nHave a good day and thank you.";
            String body="Use this code: "+code+" to finished registation";
            message.setText(body);
            Transport.send(message);
        }
        catch (Exception e)
        {
            System.out.println("SendingEmail..."+e);
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
