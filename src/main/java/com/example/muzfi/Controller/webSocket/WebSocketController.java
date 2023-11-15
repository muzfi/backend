package com.example.muzfi.Controller.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/send-notification")
    @SendTo("/topic/notification")
    public String sendNotification(String notification) {
        // Handle incoming notification and broadcast it to subscribers
        return notification;
    }
}
