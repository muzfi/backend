package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.ListingDetailsDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Model.Post.Listing;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Repository.ListingRepository;
import com.example.muzfi.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ListingRepository listingRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ListingRepository listingRepository) {
        this.postRepository = postRepository;
        this.listingRepository = listingRepository;
    }

    //Retrieve all posts
    @Override
    public Optional<List<PostDetailsDto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDetailsDto> postList = new ArrayList<>();

        for (Post post : posts) {
            Object postTypeData = getPostTypeData(post);

            if (postTypeData != null) {
                PostDetailsDto postDetailsDto = getPostDetailsDto(post, postTypeData);
                postList.add(postDetailsDto);
            }
        }

        if (postList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(postList);
    }

    //Retrieve post data by post id
    @Override
    public Optional<PostDetailsDto> getPostById(String id) {

        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Object postTypeData = getPostTypeData(post);

            if (postTypeData != null) {
                PostDetailsDto postDetailsDto = getPostDetailsDto(post, postTypeData);

                return Optional.of(postDetailsDto);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    //Retrieve data for different post types - listings, gears, polls, topics
    private Object getPostTypeData(Post post) {
        Object postTypeData = null;

        if (post.getPostType().equals(PostType.PROD_SALE)) {
            Optional<Listing> listingOptional = listingRepository.findById(post.getPostTypeId());

            if (listingOptional.isPresent()) {
                Listing listing = listingOptional.get();
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

                postTypeData = listingDetailsDto;
            } else {
                return null;
            }
        } else if (post.getPostType().equals(PostType.PROD_GEAR)) {

            //TODO: Implementation for the gear

        } else if (post.getPostType().equals(PostType.PROD_POLL)) {

            //TODO: Implementation for the poll

        } else if (post.getPostType().equals(PostType.PROD_TOPIC)) {

            //TODO: Implementation for the topic

        }

        return postTypeData;
    }

    //Creates PostDetailsDto including post details and postType details
    private PostDetailsDto getPostDetailsDto(Post post, Object postTypeData) {
        PostDetailsDto postDetailsDto = new PostDetailsDto();

        postDetailsDto.setId(post.getId());
        postDetailsDto.setAuthorId(post.getAuthorId());
        postDetailsDto.setPostTitle(post.getPostTitle());
        postDetailsDto.setPostSubTitle(post.getPostSubTitle());
        postDetailsDto.setPostTextContent(post.getPostTextContent());
        postDetailsDto.setPostType(post.getPostType().toString());
        postDetailsDto.setPostTypeId(post.getPostTypeId());
        postDetailsDto.setPostTypeData(postTypeData);
        postDetailsDto.setLikes(post.getLikes());
        postDetailsDto.setComments(post.getComments());
        postDetailsDto.setShares(post.getShares());
        postDetailsDto.setCreatedDateTime(post.getCreatedDateTime());
        postDetailsDto.setUpdatedDateTime(post.getUpdatedDateTime());
        return postDetailsDto;
    }
}