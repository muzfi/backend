package com.ecommerce.shoppingmarket.service.offerservice;

import com.ecommerce.shoppingmarket.model.Offer;

import java.util.List;

public interface OfferService {
    String makeOffer(String productId, Offer offer);
    List<Offer> getUserOffers(String userId);
}
