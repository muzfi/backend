package com.example.muzfi.Controller;

import com.example.muzfi.Dto.InvitationRequest;
import com.example.muzfi.Services.InvitationService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping("/send")
    public String sendInvitation(@RequestBody InvitationRequest request) throws MessagingException {
        invitationService.sendInvitation(request);
        return "Invitation sent successfully.";
    }

    @GetMapping("/confirm")
    public String confirmInvitation(@RequestParam String token) {
        invitationService.confirmInvitation(token);
        return "Invitation confirmed.";
    }
}
