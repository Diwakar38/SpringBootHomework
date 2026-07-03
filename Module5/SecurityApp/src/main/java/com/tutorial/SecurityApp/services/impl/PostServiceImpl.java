package com.tutorial.SecurityApp.services.impl;

import com.tutorial.SecurityApp.dtos.PostDto;
import com.tutorial.SecurityApp.entities.PostEntity;
import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.exceptions.ResourceNotFoundException;
import com.tutorial.SecurityApp.repositories.PostRepository;
import com.tutorial.SecurityApp.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("User: {}", userEntity);

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
