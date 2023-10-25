package com.ecommerce.shoppingmarket.service.offerservice;

import com.ecommerce.shoppingmarket.model.Offer;
import com.ecommerce.shoppingmarket.repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepo offerRepo;
    @Override
    public String makeOffer(String productId, Offer offer) {
            offer.setProductId(productId);
            Offer savedOffer = offerRepo.save(offer);
            return "Offer placed successfully with ID : " + savedOffer.getId();

    }

    @Override
    public List<Offer> getUserOffers(String userId) {
        return offerRepo.findByUserId(userId);
    }
}
