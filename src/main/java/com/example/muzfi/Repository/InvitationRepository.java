package com.example.muzfi.Repository;

import com.example.muzfi.Model.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvitationRepository extends MongoRepository<Invitation, String> {
    Invitation findByConfirmationToken(String confirmationToken);
}
