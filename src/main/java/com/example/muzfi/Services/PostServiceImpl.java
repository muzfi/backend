package com.example.muzfi.Services;

import com.example.muzfi.Model.Post;
import com.example.muzfi.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPost(String id) {
        return postRepository.findById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}