package com.ute.bookstoreonlinebe.services.mailsender;

import com.ute.bookstoreonlinebe.entities.Order;
import com.ute.bookstoreonlinebe.entities.User;
import com.ute.bookstoreonlinebe.models.EmailDetails;

public interface MailSenderService {
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    String sendSignup(User user);

    String sendNewOrder(User user, Order order);

    String sendCallOffOrder(User user, String orderID);

    String sendChangePassword(User user);

    String sendForgotPassword(User user);

    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
