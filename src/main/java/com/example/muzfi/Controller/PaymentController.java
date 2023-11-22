package com.example.muzfi.Controller;

import com.example.muzfi.Model.ChargeRequest;
import com.example.muzfi.Services.StripeService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/charge")
    public Charge charge(@RequestBody ChargeRequest chargeRequest) throws Exception {
        return stripeService.charge(chargeRequest);
    }
}
