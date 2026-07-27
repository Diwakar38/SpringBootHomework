package com.tutorial.SecurityApp.services;

import com.tutorial.SecurityApp.dtos.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto post);

    PostDto getPostById(Long postId);

    PostDto upadatePost(Long postId, PostDto postDto);
}
