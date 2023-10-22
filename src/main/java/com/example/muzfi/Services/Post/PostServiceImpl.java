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
import com.example.muzfi.Repository.*;
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

    private final PollOptionRepository pollOptionRepository;

    private final UserService userService;

    private final LikeService likeService;

    private final CommentService commentService;

    private final OfferService offerService;

    private final PostManager postManager;

    private final ListingManager listingManager;


    @Autowired
    public PostServiceImpl(PostRepository postRepository, ListingRepository listingRepository, TopicRepository topicRepository, PollRepository pollRepository, PollOptionRepository pollOptionRepository, UserService userService, LikeService likeService, CommentService commentService, OfferService offerService, PostManager postManager, ListingManager listingManager) {
        this.postRepository = postRepository;
        this.listingRepository = listingRepository;
        this.topicRepository = topicRepository;
        this.pollRepository = pollRepository;
        this.pollOptionRepository = pollOptionRepository;
        this.userService = userService;
        this.likeService = likeService;
        this.commentService = commentService;
        this.offerService = offerService;
        this.postManager = postManager;
        this.listingManager = listingManager;
    }

    //Retrieve all posts - not draft
    @Override
    public Optional<List<PostDetailsDto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDetailsDto> postList = new ArrayList<>();

        for (Post post : posts) {
            //Filter out draft posts
            if (post.getIsDraft() != null && post.getIsDraft().equals(true)) {
                continue;
            }

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

    //Retrieve posts by user id - not draft
    @Override
    public Optional<List<PostDetailsDto>> getPostsByUserId(String userId) {

        List<Post> postList = postRepository.findAllByAuthorId(userId);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(userId);

        List<PostDetailsDto> postDetailsDtoList = new ArrayList<>();

        if (!postList.isEmpty() && authorOptional.isPresent()) {
            PostAuthorDto author = authorOptional.get();

            for (Post post : postList) {
                //Filter out draft posts
                if (post.getIsDraft() != null && post.getIsDraft().equals(true)) {
                    continue;
                }

                Object postTypeData = getPostTypeData(post);

                if (postTypeData != null) {
                    PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(post, postTypeData, author);
                    postDetailsDtoList.add(postDetailsDto);
                }
            }

            return Optional.of(postDetailsDtoList);
        } else {
            return Optional.empty();
        }
    }


    //Retrieve posts by user id - draft post
    @Override
    public Optional<List<PostDetailsDto>> getDraftPostsByUserId(String userId) {

        List<Post> postList = postRepository.findAllByAuthorIdAndIsDraft(userId, true);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(userId);

        List<PostDetailsDto> postDetailsDtoList = new ArrayList<>();

        if (!postList.isEmpty() && authorOptional.isPresent()) {
            PostAuthorDto author = authorOptional.get();

            for (Post post : postList) {

                Object postTypeData = getPostTypeData(post);

                if (postTypeData != null) {
                    PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(post, postTypeData, author);
                    postDetailsDtoList.add(postDetailsDto);
                }
            }

            return Optional.of(postDetailsDtoList);
        } else {
            return Optional.empty();
        }
    }

    //Publish drafted posts
    @Override
    public Optional<PostDetailsDto> publishDraftPost(String postId) {

        Optional<Post> postOpt = postRepository.findById(postId);

        if (postOpt.isEmpty()) {
            return Optional.empty();
        }

        Post existingPost = postOpt.get();
        existingPost.setIsDraft(false);

        Post updatedPost = postRepository.save(existingPost);

        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(updatedPost.getAuthorId());
        PostDetailsDto postDetailsDto = new PostDetailsDto();

        if (authorOptional.isPresent()) {
            PostAuthorDto author = authorOptional.get();
            Object postTypeData = getPostTypeData(updatedPost);
            postDetailsDto = postManager.getPostDetailsDto(updatedPost, postTypeData, author);
        }

        return Optional.of(postDetailsDto);
    }

    //delete post
    @Override
    public Optional<String> deletePost(String postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            //Delete likes
            Optional<List<String>> likeIdsOpt = likeService.getLikeIdsByPostId(postId);

            if (likeIdsOpt.isPresent()) {
                likeService.deleteLikesByIds(likeIdsOpt.get());
            }

            //Delete comments
            Optional<List<String>> commentIdsOpt = commentService.getCommentIdsByPostId(postId);

            if (commentIdsOpt.isPresent()) {
                commentService.deleteCommentsByIds(commentIdsOpt.get());
            }

            //Delete post type data
            if (post.getPostType().equals(PostType.PROD_SALE)) {

                //Delete offers
                Optional<List<String>> offerIdsOpt = offerService.getOfferIdsByListingId(post.getPostTypeId());

                if (offerIdsOpt.isPresent()) {
                    offerService.deleteOffersByIds(offerIdsOpt.get());
                }

                //Delete Listing
                listingRepository.deleteById(post.getPostTypeId());
            } else if (post.getPostType().equals(PostType.PROD_POLL)) {

                //Delete poll options
                Optional<Poll> pollOpt = pollRepository.findById(post.getPostTypeId());

                if (pollOpt.isPresent()) {
                    pollOptionRepository.deleteAllById(pollOpt.get().getPollOptionIds());
                }

                //Delete poll
                pollRepository.deleteById(post.getPostTypeId());
            } else if (post.getPostType().equals(PostType.PROD_TOPIC)) {

                //Delete post
                topicRepository.deleteById(post.getPostTypeId());
            } else if (post.getPostType().equals(PostType.PROD_GEAR)) {

                //TODO: Implementation for the gear

            }

            //Delete post
            postRepository.deleteById(post.getId());

            return Optional.of("Post Deleted Successfully");
        }

        return Optional.empty();
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