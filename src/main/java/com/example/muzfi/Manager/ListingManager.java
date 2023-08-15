package com.example.muzfi.Manager;

import com.example.muzfi.Dto.PostDto.ListingDetailsDto;
import com.example.muzfi.Model.Post.Listing;
import org.springframework.stereotype.Component;

@Component
public class ListingManager {

    //Create ListingDetailsDto
    public ListingDetailsDto getListingDetailsDto(Listing listing) {
        ListingDetailsDto listingDetailsDto = new ListingDetailsDto();

        listingDetailsDto.setId(listing.getId());
        listingDetailsDto.setPostId(listing.getPostId());
        listingDetailsDto.setAuthorId(listing.getAuthorId());
        listingDetailsDto.setBrand(listing.getBrand());
        listingDetailsDto.setModel(listing.getModel());
        listingDetailsDto.setYear(listing.getYear());
        listingDetailsDto.setFinish(listing.getFinish());
        listingDetailsDto.setTitle(listing.getTitle());
        listingDetailsDto.setIsHandMade(listing.getIsHandMade());
        listingDetailsDto.setImages(listing.getImages());
        listingDetailsDto.setCondition(listing.getCondition());
        listingDetailsDto.setConditionDescription(listing.getConditionDescription());
        listingDetailsDto.setYouTubeLink(listing.getYouTubeLink());
        listingDetailsDto.setDeliverMethod(listing.getDeliverMethod());
        listingDetailsDto.setShippingDetails(listing.getShippingDetails());
        listingDetailsDto.setPrice(listing.getPrice());
        listingDetailsDto.setIs3PercentFromFinalSellingPrice(listing.getIs3PercentFromFinalSellingPrice());
        listingDetailsDto.setIsAcceptOffers(listing.getIsAcceptOffers());
        listingDetailsDto.setBumpRate(listing.getBumpRate());
        listingDetailsDto.setCreatedDateTime(listing.getCreatedDateTime());
        listingDetailsDto.setUpdatedDateTime(listing.getUpdatedDateTime());
        return listingDetailsDto;
    }
}
