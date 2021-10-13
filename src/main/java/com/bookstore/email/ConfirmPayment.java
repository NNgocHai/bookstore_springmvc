package com.bookstore.email;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ConfirmPayment {
    private int Ma_DH;
    private String userEmail;
    public ConfirmPayment(int Ma_DH,String userEmail)
    {
        this.Ma_DH=Ma_DH;
        this.userEmail=userEmail;
        final String  email="cuahangsachso6@gmail.com";
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
            message.setSubject("Order Successfull");
            String confirmPayment="Thank you for buying books at my shop,\n\n"
                    + "Your Order Code is :: "+Ma_DH+"\n"
                    +"Delivery time is expected to be 2-3 days\n"
                    +"You check your phone to receive the books"
                    + "\nHave a good day and thank you.";
            message.setText(confirmPayment);
            Transport.send(message);
        }
        catch (Exception e)
        {
            System.out.println("SendingEmail..."+e);
        }
    }
}
