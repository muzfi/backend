package com.example.muzfi.Services;

import com.example.muzfi.Model.Message;
import com.example.muzfi.Repository.MessageRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl {
    private final MessageRepository messageRepository;
    private final EmailNotificationService emailNotificationService;
    private final SimpMessagingTemplate messagingTemplate; // Inject the SimpMessagingTemplate

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, EmailNotificationService emailNotificationService, SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.emailNotificationService = emailNotificationService;
        this.messagingTemplate = messagingTemplate;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(Message message) {
        // Save the message
        Message savedMessage = messageRepository.save(message);

        // Send an email notification for the new message
        String recipient = message.getRecipient();
        String subject = "New Message Notification";
        String messageText = message.getContent();
        emailNotificationService.sendMessage(recipient, subject, messageText);

        // Notify WebSocket clients about the new message
        notifyNewMessage(savedMessage);

        return savedMessage;
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }

    private void notifyNewMessage(Message message) {
        // Notify subscribers of the "/topic/new-message" channel
        messagingTemplate.convertAndSend("/topic/new-message", message);
    }
}
