package com.example.muzfi.Services;

import com.example.muzfi.Dto.InvitationRequest;
import com.example.muzfi.Model.Invitation;
import com.example.muzfi.Repository.InvitationRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InvitationService {




    @Autowired
    private final InvitationRepository invitationRepository;
    @Autowired
    private final EmailConfirmationService emailConfirmationService;



    public void sendInvitation(InvitationRequest request) throws MessagingException {
        // Generate a random confirmation token
        String confirmationToken = UUID.randomUUID().toString();

        // Create the invitation
        Invitation invitation = new Invitation(request.getSenderId(), request.getRecipientEmail(), confirmationToken);

        // Save the invitation in the database
        invitationRepository.save(invitation);

        // Send a confirmation email
        emailConfirmationService.sendInvitationEmail(request.getRecipientEmail(), confirmationToken);
    }

    public void confirmInvitation(String confirmationToken) {
        Invitation invitation = invitationRepository.findByConfirmationToken(confirmationToken);
        if (invitation != null) {
            // Handle registration logic, e.g., create a new user account
            // Mark the invitation as confirmed
            invitation.setConfirmed(true);
            invitationRepository.save(invitation);
        } else {
            throw new IllegalArgumentException("Invalid confirmation token.");
        }
    }
}
