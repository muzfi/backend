package com.example.muzfi.Services.EmailConfirmationService;

import com.example.muzfi.Model.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service

public class EmailConfirmationService {
    private final JavaMailSender javaMailSender;

    private TemplateEngine templateEngine;

    @Autowired
    public EmailConfirmationService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
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

    public void sendCommunityCreatedConfirmation(String recipientEmail, String communityName) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String emailContent = generateCommunityCreatedEmailContent(communityName);

        helper.setTo(recipientEmail);
        helper.setSubject("New Community Created");
        helper.setText(emailContent, true);

        javaMailSender.send(message);
    }

    private String generateCommunityCreatedEmailContent(String communityName) {
        // Use a templating engine to create the email content
        // Include the community name and a message about the new community
        Context context = new Context();
        context.setVariable("communityName", communityName);

        return templateEngine.process("community_created_email_template", context);
    }

    public  void sendSignUpConfirmationEmail(String email, String confirmationToken) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Set the recipient's email address
        helper.setTo(email);

        // Set the email subject
        helper.setSubject("Confirm Your Signup");

        // Create a Thymeleaf context for email template variables
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("token", confirmationToken);

        // Process the email template using Thymeleaf
        String htmlContent = templateEngine.process("signup_confirmation_email_template", context);

        // Set the email content as HTML
        helper.setText(htmlContent, true);

        // Send the email
        javaMailSender.send(mimeMessage);
    }

}
