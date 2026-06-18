package com.tutorial.prod_ready_features.services;

import com.tutorial.prod_ready_features.dtos.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto post);

    PostDto getPostById(Long postId);

    PostDto upadatePost(Long postId, PostDto postDto);
}
