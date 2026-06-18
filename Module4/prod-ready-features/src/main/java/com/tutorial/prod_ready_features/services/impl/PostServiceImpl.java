package com.tutorial.prod_ready_features.services.impl;

import com.tutorial.prod_ready_features.dtos.PostDto;
import com.tutorial.prod_ready_features.entities.PostEntity;
import com.tutorial.prod_ready_features.exceptions.ResourceNotFoundException;
import com.tutorial.prod_ready_features.repositories.PostRepository;
import com.tutorial.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(postEntity -> {
            return modelMapper.map(postEntity, PostDto.class);
        }).toList();
    }

    @Override
    public PostDto createNewPost(PostDto post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.getId());
        postEntity.setTitle(post.getTitle());
        postEntity.setDescription(post.getDescription());
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + postId + " was not found!"));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto upadatePost(Long postId, PostDto postDto) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + postId + " was not found!"));
        PostEntity updatedEntity = modelMapper.map(postDto, PostEntity.class);
        updatedEntity.setId(postId);
        return modelMapper.map(postRepository.save(updatedEntity), PostDto.class);
    }
}
