package com.example.muzfi.Manager;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Post;
import org.springframework.stereotype.Component;

@Component
public class PostManager {

    //Creates PostDetailsDto including post details and postType details
    public PostDetailsDto getPostDetailsDto(Post post, Object postTypeData) {
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
