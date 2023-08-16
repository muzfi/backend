package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.ListingFeedDto;
import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.ListingManager;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Listing;
import com.example.muzfi.Model.Post.Poll;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.Post.Topic;
import com.example.muzfi.Repository.ListingRepository;
import com.example.muzfi.Repository.PollRepository;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Repository.TopicRepository;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ListingRepository listingRepository;

    private final TopicRepository topicRepository;

    private final PollRepository pollRepository;

    private final UserService userService;

    private final PostManager postManager;

    private final ListingManager listingManager;


    @Autowired
    public PostServiceImpl(PostRepository postRepository, ListingRepository listingRepository, TopicRepository topicRepository, PollRepository pollRepository, UserService userService, PostManager postManager, ListingManager listingManager) {
        this.postRepository = postRepository;
        this.listingRepository = listingRepository;
        this.topicRepository = topicRepository;
        this.pollRepository = pollRepository;
        this.userService = userService;
        this.postManager = postManager;
        this.listingManager = listingManager;
    }

    //Retrieve all posts
    @Override
    public Optional<List<PostDetailsDto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDetailsDto> postList = new ArrayList<>();

        for (Post post : posts) {
            Object postTypeData = getPostTypeData(post);
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());

            if (postTypeData != null && authorOptional.isPresent()) {
                PostAuthorDto author = authorOptional.get();
                PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(post, postTypeData, author);
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
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());

            if (postTypeData != null && authorOptional.isPresent()) {
                PostAuthorDto author = authorOptional.get();
                PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(post, postTypeData, author);

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
                ListingFeedDto listingFeedDto = listingManager.getListingFeedDto(listing);

                postTypeData = listingFeedDto;
            } else {
                return null;
            }
        } else if (post.getPostType().equals(PostType.PROD_GEAR)) {

            //TODO: Implementation for the gear

        } else if (post.getPostType().equals(PostType.PROD_POLL)) {

            Optional<Poll> pollOptional = pollRepository.findById(post.getPostTypeId());

            if (pollOptional.isPresent()) {
                Poll poll = pollOptional.get();

                postTypeData = poll;
            } else {
                return null;
            }

        } else if (post.getPostType().equals(PostType.PROD_TOPIC)) {

            Optional<Topic> topicOptional = topicRepository.findById(post.getPostTypeId());

            if (topicOptional.isPresent()) {
                Topic topic = topicOptional.get();

                postTypeData = topic;
            } else {
                return null;
            }

        }

        return postTypeData;
    }
}