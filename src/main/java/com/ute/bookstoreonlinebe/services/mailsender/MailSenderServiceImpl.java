package com.ute.bookstoreonlinebe.services.mailsender;

import com.ute.bookstoreonlinebe.entities.Order;
import com.ute.bookstoreonlinebe.entities.User;
import com.ute.bookstoreonlinebe.models.EmailDetails;
import com.ute.bookstoreonlinebe.models.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class MailSenderServiceImpl implements MailSenderService{
    @Autowired
    private JavaMailSender javaMailSender;

    private MyConstants myConstants = new MyConstants();

    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(myConstants.MY_EMAIL);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendSignup(User user) {
        String subject = "Welcome FESHBOOk!";
        String msgBody = String.format("Chào mừng %s đã đến với FESHBOOK!", user.getFullname());

        return sendSimpleMail(new EmailDetails(user.getEmail(), msgBody,subject,null));
    }

    @Override
    public String sendNewOrder(User user, Order order) {
        String subject = "Welcome FESHBOOk!";
        String msgBodyXC = String.format("Xin chào:  %s", user.getFullname());
        String msgBodyCT = String.format("\nBạn đã đặt hàng thành công, với mã đơn hàng là: %s ", order.getId());
        String msgOrderTime = String.format("\nVào lúc: %s", order.getOrderDate());
        String msgTime = "\nThơi gian giao hàng dự kiến từ 3 đến 5 ngày, không tính thứ 7 và chủ nhật.";
        String msgBody = msgBodyXC + msgBodyCT + msgOrderTime + msgTime;
        return sendSimpleMail(new EmailDetails(user.getEmail(), msgBody, subject, null));
    }

    @Override
    public String sendCallOffOrder(User user, String orderID) {
        String subject = "Welcome FESHBOOk!";
        String msgBodyXC = String.format("Xin chào:  %s", user.getFullname());
        String msgBodyCT = String.format("\nBạn đã hũy đơn hàng có mã: %s ", orderID);
        String msgTime = String.format(" vào lúc: %s", new Date());
        String msgBody = msgBodyXC + msgBodyCT + msgTime;
        return sendSimpleMail(new EmailDetails(user.getEmail(), msgBody, subject, null));
    }

    @Override
    public String sendChangePassword(User user) {
        String subject = "Welcome FESHBOOk!";
        String msgBodyXC = String.format("Xin chào:  %s", user.getFullname());
        String msgBodyCT = String.format("\nBạn đã thực hiện thay đổi mật khẩu thành công vào lúc %s", new Date());
        String msgBodyWRN = "\nNếu bạn đó không phải bạn, hay liện hệ với chúng tôi ngay theo email sau: zerodev47@gmail.com";
        String msgBody = msgBodyXC + msgBodyCT + msgBodyWRN;
        return sendSimpleMail(new EmailDetails(user.getEmail(), msgBody, subject, null));
    }

    @Override
    public String sendForgotPassword(User user) {
        return null;
    }

    // Method 2
    // To send an email with attachment
    public String sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(myConstants.MY_EMAIL);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
