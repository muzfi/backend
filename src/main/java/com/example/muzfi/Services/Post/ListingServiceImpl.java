package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.*;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.ListingManager;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Listing;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Repository.ListingRepository;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListingServiceImpl implements ListingService {

    private final PostRepository postRepository;

    private final ListingRepository listingRepository;

    private final UserService userService;

    private final PostManager postManager;

    private final ListingManager listingManager;

    @Autowired
    public ListingServiceImpl(PostRepository postRepository, ListingRepository listingRepository, UserService userService, PostManager postManager, ListingManager listingManager) {
        this.postRepository = postRepository;
        this.listingRepository = listingRepository;
        this.userService = userService;
        this.postManager = postManager;
        this.listingManager = listingManager;
    }

    //get listings - filtered draft listings
    @Override
    public Optional<List<ListingDetailsDto>> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        List<ListingDetailsDto> listingList = new ArrayList<>();

        for (Listing listing : listings) {
            Optional<Post> postOpt = postRepository.findById(listing.getPostId());

            if (postOpt.isPresent()) {
                Post post = postOpt.get();

                if (post.getIsDraft() == null || post.getIsDraft().equals(false)) {
                    ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                    listingList.add(dto);
                }
            }
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

    //Get user listings - filtered draft listings
    @Override
    public Optional<List<ListingDetailsDto>> getListingsByUserId(String userId) {
        List<Listing> listings = listingRepository.findAllByAuthorId(userId);

        if (!listings.isEmpty()) {
            List<ListingDetailsDto> listingDtoList = new ArrayList<>();

            for (Listing listing : listings) {
                Optional<Post> postOpt = postRepository.findById(listing.getPostId());

                if (postOpt.isPresent()) {
                    Post post = postOpt.get();

                    if (post.getIsDraft() == null || post.getIsDraft().equals(false)) {
                        ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                        listingDtoList.add(dto);
                    }
                }
            }

            return Optional.of(listingDtoList);
        } else {
            return Optional.empty();
        }
    }

    //Get draft user listings
    @Override
    public Optional<List<ListingDetailsDto>> getDraftListingsByUserId(String userId) {
        List<Listing> listings = listingRepository.findAllByAuthorId(userId);

        if (!listings.isEmpty()) {
            List<ListingDetailsDto> listingDtoList = new ArrayList<>();

            for (Listing listing : listings) {
                Optional<Post> postOpt = postRepository.findById(listing.getPostId());

                if (postOpt.isPresent()) {
                    Post post = postOpt.get();

                    if (post.getIsDraft() != null && post.getIsDraft().equals(true)) {
                        ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                        listingDtoList.add(dto);
                    }
                }
            }

            return Optional.of(listingDtoList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PostDetailsDto> createListing(ListingCreateDto listingDto) {
        //create post
        Post newPost = new Post();
        newPost.setAuthorId(listingDto.getAuthorId());
        newPost.setPostType(PostType.PROD_SALE);
        newPost.setIsEnablePostReplyNotification(listingDto.getIsEnablePostReplyNotification());
        newPost.setCreatedDateTime(LocalDateTime.now());
        newPost.setUpdatedDateTime(LocalDateTime.now());
        newPost.setIsDraft(listingDto.getIsDraft());

        //create listing
        Listing newListing = new Listing();
        newListing.setAuthorId(listingDto.getAuthorId());
        newListing.setBrand(listingDto.getBrand());
        newListing.setModel(listingDto.getModel());
        newListing.setYear(listingDto.getYear());
        newListing.setShippingDetails(listingDto.getShippingDetails());
        newListing.setFinish(listingDto.getFinish());
        newListing.setTitle(listingDto.getTitle());
        newListing.setSubTitle(listingDto.getSubTitle());
        newListing.setDescription(listingDto.getDescription());
        newListing.setIsHandMade(listingDto.getIsHandMade());
        newListing.setImages(listingDto.getImages());
        newListing.setCreatedDateTime(LocalDateTime.now());
        newListing.setUpdatedDateTime(LocalDateTime.now());
        newListing.setCondition(listingDto.getCondition());
        newListing.setConditionDescription(listingDto.getConditionDescription());
        newListing.setYouTubeLink(listingDto.getYouTubeLink());
        newListing.setDeliverMethod(listingDto.getDeliverMethod());
        newListing.setPrice(listingDto.getPrice());
        newListing.setIs3PercentFromFinalSellingPrice(listingDto.getIs3PercentFromFinalSellingPrice());
        newListing.setIsAcceptOffers(listingDto.getIsAcceptOffers());
        newListing.setBumpRate(listingDto.getBumpRate());
        newListing.setTags(listingDto.getTags());
        newListing.setDeadline(listingDto.getDeadline());

        //save post and listing
        Post post = postRepository.save(newPost);
        Listing listing = listingRepository.save(newListing);

        //update post and listing with ids
        post.setPostTypeId(listing.getId());
        listing.setPostId(post.getId());

        Post postUpdated = postRepository.save(post);
        Listing listingUpdated = listingRepository.save(listing);

        //return created post
        ListingDetailsDto listingDetailsDto = listingManager.getListingDetailsDto(listingUpdated);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, listingDetailsDto, authorOptional.get());

        return Optional.ofNullable(postDetailsDto);
    }

    @Override
    public Optional<PostDetailsDto> updateListing(ListingUpdateDto updateDto) {
        Optional<Listing> listingOpt = listingRepository.findById(updateDto.getId());
        Optional<Post> postOpt = postRepository.findById(updateDto.getPostId());

        if (listingOpt.isEmpty() || postOpt.isEmpty()) {
            return Optional.empty();
        }

        Listing listing = listingOpt.get();
        Post post = postOpt.get();

        //set listing
        listing.setBrand(updateDto.getBrand());
        listing.setModel(updateDto.getModel());
        listing.setYear(updateDto.getYear());
        listing.setFinish(updateDto.getFinish());
        listing.setTitle(updateDto.getTitle());
        listing.setSubTitle(updateDto.getSubTitle());
        listing.setDescription(updateDto.getDescription());
        listing.setIsHandMade(updateDto.getIsHandMade());
        listing.setImages(updateDto.getImages());
        listing.setCondition(updateDto.getCondition());
        listing.setConditionDescription(updateDto.getConditionDescription());
        listing.setYouTubeLink(updateDto.getYouTubeLink());
        listing.setDeliverMethod(updateDto.getDeliverMethod());
        listing.setShippingDetails(updateDto.getShippingDetails());
        listing.setPrice(updateDto.getPrice());
        listing.setIs3PercentFromFinalSellingPrice(updateDto.getIs3PercentFromFinalSellingPrice());
        listing.setIsAcceptOffers(updateDto.getIsAcceptOffers());
        listing.setBumpRate(updateDto.getBumpRate());
        listing.setDeadline(updateDto.getDeadline());
        listing.setTags(updateDto.getTags());
        listing.setUpdatedDateTime(LocalDateTime.now());

        post.setUpdatedDateTime(LocalDateTime.now());

        Post postUpdated = postRepository.save(post);
        Listing listingUpdated = listingRepository.save(listing);

        ListingDetailsDto listingDetailsDto = listingManager.getListingDetailsDto(listingUpdated);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, listingDetailsDto, authorOptional.get());

        return Optional.of(postDetailsDto);
    }


    //Including draft posts
    @Override
    public Optional<List<ListingDetailsDto>> getListingsCreatedByUserInCurrentMonth(String userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);

        List<Listing> listings = listingRepository.findListingsCreatedByUserInCurrentMonth(userId, startOfMonth, startOfNextMonth);

        if (!listings.isEmpty()) {
            List<ListingDetailsDto> listingDtoList = new ArrayList<>();

            for (Listing listing : listings) {
                ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                listingDtoList.add(dto);
            }

            return Optional.of(listingDtoList);
        } else {
            return Optional.empty();
        }
    }

}
