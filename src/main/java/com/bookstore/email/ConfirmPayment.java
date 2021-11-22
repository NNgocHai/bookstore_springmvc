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
public class ConfirmPayment {
    @Autowired
    ServletContext context;

    @Autowired
    JavaMailSender mailSender;


    public void ConfirmPayment(int Ma_DH,String userEmail)
    {
        final String  email="cuahangsachso6@gmail.com";
        final String pword="Tu18110387";
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
            helper.setFrom(email);
            helper.setTo(userEmail);
            helper.setReplyTo(email);
            helper.setSubject("Đặt hàng thành công");
            String body="<p>Cảm ơn bạn đã mua sách</p>"
                    + "<p>Mã đơn hàng của bạn là :: "+"<span>"+String.valueOf(Ma_DH)+"</span>"+"</p>"
                    +"<p>Đơn hàng sẽ có sau 2-3 ngàys</p>"
                    +"<p>Bạn vui lòng kiểm tra điện thoại khi shipper liên lạc</p>"
                    + "<p>Chúc bạn ngày mới tốt lành!!!<p>";
            String confirmPayment="Thank you for buying books at my shop,\n\n"
                    + "Your Order Code is :: "+Ma_DH+"\n"
                    +"Delivery time is expected to be 2-3 days\n"
                    +"You check your phone to receive the books"
                    + "\nHave a good day and thank you.";
            helper.setText(body,true);
            mailSender.send(message);
        }
        catch(Exception ex){
            System.out.println("SendingEmail..."+ex);
        }
    }
}
