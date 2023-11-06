package com.example.muzfi.Services;

import com.example.muzfi.Model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> getAllMessages();

    Optional<Message> getMessageById(String id);

    Message saveMessage(Message message);

    void deleteMessage(String id);
}
