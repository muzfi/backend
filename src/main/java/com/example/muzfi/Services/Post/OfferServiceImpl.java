package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.OfferUpdateDto;
import com.example.muzfi.Model.Post.Offer;
import com.example.muzfi.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    //Create offer
    @Override
    public Optional<Offer> createOffer(Offer offer) {

        Offer newOffer = new Offer();
        newOffer.setUserId(offer.getUserId());
        newOffer.setListingId(offer.getListingId());
        newOffer.setOfferAmount(offer.getOfferAmount());
        newOffer.setComment(offer.getComment());
        newOffer.setCreatedDateTime(LocalDateTime.now());
        newOffer.setUpdatedDateTime(LocalDateTime.now());

        Offer offerSaved = offerRepository.save(offer);

        return Optional.of(offerSaved);
    }

    //get offers by id
    @Override
    public Optional<Offer> getOfferById(String id) {
        return offerRepository.findById(id);
    }

    //get offers by listing id
    @Override
    public Optional<List<Offer>> getOffersByListingId(String listingId) {

        List<Offer> offers = offerRepository.findAllByListingId(listingId);

        if (offers.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(offers);
    }

    //get offers by user id
    @Override
    public Optional<List<Offer>> getOffersByUserId(String userId) {

        List<Offer> offers = offerRepository.findAllByUserId(userId);

        if (offers.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(offers);
    }

    //update offer
    @Override
    public Optional<Offer> updateOffer(OfferUpdateDto offerDto) {

        Optional<Offer> offerOpt = getOfferById(offerDto.getId());

        if (offerOpt.isEmpty()) {
            return Optional.empty();
        }

        Offer existingOffer = offerOpt.get();
        existingOffer.setOfferAmount(offerDto.getOfferAmount());
        existingOffer.setComment(offerDto.getComment());
        existingOffer.setUpdatedDateTime(LocalDateTime.now());

        Offer offerSaved = offerRepository.save(existingOffer);

        return Optional.of(offerSaved);
    }

    //delete offer
    @Override
    public Optional<String> deleteOffer(String offerId) {

        Optional<Offer> offerOpt = getOfferById(offerId);

        if (offerOpt.isEmpty()) {
            return Optional.empty();
        }

        offerRepository.deleteById(offerId);

        return Optional.of("Offer Deleted Successfully");
    }
}
