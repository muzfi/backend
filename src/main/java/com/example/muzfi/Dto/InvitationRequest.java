package com.example.muzfi.Dto;

import lombok.*;

@Data
@AllArgsConstructor

@Getter
@Setter
public class InvitationRequest {
    private String senderId;
    private String recipientEmail;


}
