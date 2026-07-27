package com.tutorial.SecurityApp.util;

import com.tutorial.SecurityApp.dtos.PostDto;
import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostSecurity {
    private final PostService postService;

    public boolean isOwnerOfPost(Long postId) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDto post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user != null ? user.getId() : null);
    }
}
