package com.example.muzfi.Services;

import com.example.muzfi.Model.ChargeRequest;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Override
    public Charge charge(ChargeRequest chargeRequest) throws StripeException,
            APIException {
        EmailNotificationService emailNotificationService = new EmailNotificationService();
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        emailNotificationService.sendPayoutConfirmationAlert(chargeRequest.getStripeEmail(),chargeRequest.getAmount());
        return Charge.create(chargeParams);
    }


}

