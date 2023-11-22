package com.example.muzfi.Services;

import com.example.muzfi.Model.ChargeRequest;
import com.stripe.model.Charge;

public interface StripeService {
    Charge charge(ChargeRequest chargeRequest) throws Exception, APIException;
}
