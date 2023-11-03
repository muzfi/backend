package com.example.muzfi.Services.EmailConfirmationService;

import com.example.muzfi.Model.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service

public class EmailConfirmationService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailConfirmationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNewListingConfirmation(String sellerEmail, Product newProduct) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(sellerEmail);
        helper.setSubject("New Listing Confirmation");
        helper.setText("Your new listing has been confirmed!\n\n" +
                "Listing Name: " + newProduct.getName() + "\n" +
                "Category: " + newProduct.getCategory() + "\n" +
                "Price: $" + newProduct.getPrice());

        javaMailSender.send(message);
    }
    public void sendInvitationEmail(String recipientEmail, String confirmationToken) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Invitation Confirmation");
        helper.setText("To accept the invitation, please click the following link: "
                + "http://your-app-url/invitations/confirm?token=" + confirmationToken);

        javaMailSender.send(message);
    }
}
