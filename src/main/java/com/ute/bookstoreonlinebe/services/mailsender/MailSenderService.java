package com.ute.bookstoreonlinebe.services.mailsender;

import com.ute.bookstoreonlinebe.models.EmailDetails;

public interface MailSenderService {
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
