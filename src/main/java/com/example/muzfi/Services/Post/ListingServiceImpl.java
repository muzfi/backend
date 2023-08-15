package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.ListingCreateCreateDto;
import com.example.muzfi.Dto.PostDto.ListingDetailsDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.ListingManager;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Listing;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.Post.ProductShippingDetails;
import com.example.muzfi.Repository.ListingRepository;
import com.example.muzfi.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ListingServiceImpl implements ListingService {

    private final PostRepository postRepository;

    private final ListingRepository listingRepository;

    private final PostManager postManager;

    private final ListingManager listingManager;

    @Autowired
    public ListingServiceImpl(PostRepository postRepository, ListingRepository listingRepository, PostManager postManager, ListingManager listingManager) {
        this.postRepository = postRepository;
        this.listingRepository = listingRepository;
        this.postManager = postManager;
        this.listingManager = listingManager;
    }

    @Override
    public Optional<List<ListingDetailsDto>> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        List<ListingDetailsDto> listingList = new ArrayList<>();

        for (Listing listing : listings) {
            ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
            listingList.add(dto);
        }

        if (listingList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(listingList);
    }

    @Override
    public Optional<ListingDetailsDto> getListingById(String listingId) {
        Optional<Listing> listingOptional = listingRepository.findById(listingId);

        if (listingOptional.isPresent()) {
            Listing listing = listingOptional.get();
            ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);

            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PostDetailsDto> createListing(ListingCreateCreateDto listingDto) {
        //create post
        Post newPost = new Post();
        newPost.setAuthorId(listingDto.getAuthorId());
        newPost.setPostTitle(listingDto.getListingTitle());
        newPost.setPostType(PostType.PROD_SALE);
        newPost.setIsEnablePostReplyNotification(listingDto.getIsEnablePostReplyNotification());

        //shipping details
        if (listingDto.getShippingDetails() != null) {
            ProductShippingDetails shippingDetails = new ProductShippingDetails();
            shippingDetails.setRate(listingDto.getShippingDetails().getRate());
            shippingDetails.setIsFreeShipping(listingDto.getShippingDetails().getIsFreeShipping());
            shippingDetails.setIsFlatShippingRateForRegion(listingDto.getShippingDetails().getIsFlatShippingRateForRegion());
            shippingDetails.setIsCombineShippingRate(listingDto.getShippingDetails().getIsCombineShippingRate());
        }

        //create listing
        Listing newListing = new Listing();
        newListing.setAuthorId(listingDto.getAuthorId());
        newListing.setBrand(listingDto.getBrand());
        newListing.setModel(listingDto.getModel());
        newListing.setYear(listingDto.getYear());
        newListing.setFinish(listingDto.getFinish());
        newListing.setTitle(listingDto.getListingTitle());
        newListing.setIsHandMade(listingDto.getIsHandMade());
        newListing.setImages(listingDto.getImages());
        newListing.setCondition(listingDto.getCondition());
        newListing.setConditionDescription(listingDto.getConditionDescription());
        newListing.setYouTubeLink(listingDto.getYouTubeLink());
        newListing.setShippingDetails(listingDto.getShippingDetails());
        newListing.setDeliverMethod(listingDto.getDeliverMethod());
        newListing.setPrice(listingDto.getPrice());
        newListing.setIs3PercentFromFinalSellingPrice(listingDto.getIs3PercentFromFinalSellingPrice());
        newListing.setIsAcceptOffers(listingDto.getIsAcceptOffers());
        newListing.setBumpRate(listingDto.getBumpRate());

        //save post and listing
        Post post = postRepository.save(newPost);
        Listing listing = listingRepository.save(newListing);

        //update post and listing with ids
        post.setPostTypeId(listing.getId());
        listing.setPostId(post.getId());

        Post postUpdated = postRepository.save(post);
        Listing listingUpdated = listingRepository.save(listing);

        //return created post
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, listingUpdated);

        return Optional.ofNullable(postDetailsDto);
    }

}
