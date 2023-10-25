package com.ecommerce.shoppingmarket.controller;

import com.ecommerce.shoppingmarket.model.Offer;
import com.ecommerce.shoppingmarket.service.offerservice.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/products/{id}/offer") // POST /offers/products/{id}/offer
    public String makeOffer(@PathVariable String id, @RequestBody Offer offer) {
        return offerService.makeOffer(id, offer);
    }

    @GetMapping("/users/{userId}/offers") // GET /offers/users/{userId}/offers
    public List<Offer> getUserOffers(@PathVariable String userId) {
        return offerService.getUserOffers(userId);
    }
}
