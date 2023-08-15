package com.example.muzfi.Manager;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Post;
import org.springframework.stereotype.Component;

@Component
public class PostManager {

    //Creates PostDetailsDto including post details and postType details
    public PostDetailsDto getPostDetailsDto(Post post, Object postTypeData, PostAuthorDto author) {
        PostDetailsDto postDetailsDto = new PostDetailsDto();

        postDetailsDto.setId(post.getId());
        postDetailsDto.setAuthor(author);
        postDetailsDto.setType(post.getPostType().toString());
        postDetailsDto.setPostContent(postTypeData);
        postDetailsDto.setShares(post.getShares());
        postDetailsDto.setCreatedDateTime(post.getCreatedDateTime());
        postDetailsDto.setUpdatedDateTime(post.getUpdatedDateTime());

        //TODO: Set like count

        //TODO: Set comment count

        return postDetailsDto;
    }
}
