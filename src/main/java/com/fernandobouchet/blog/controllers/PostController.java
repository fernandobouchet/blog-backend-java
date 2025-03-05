package com.fernandobouchet.blog.controllers;

import com.fernandobouchet.blog.domain.CreatePostRequest;
import com.fernandobouchet.blog.domain.UpdatePostRequest;
import com.fernandobouchet.blog.domain.dtos.CreatePostRequestDto;
import com.fernandobouchet.blog.domain.dtos.PostDto;
import com.fernandobouchet.blog.domain.dtos.UpdatePostRequestDto;
import com.fernandobouchet.blog.domain.entities.Post;
import com.fernandobouchet.blog.domain.entities.User;
import com.fernandobouchet.blog.mappers.PostMapper;
import com.fernandobouchet.blog.services.PostService;
import com.fernandobouchet.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false)UUID categoryId,
            @RequestParam(required = false) UUID tagId
            ) {
       List<Post> posts =  postService.getAllPosts(categoryId,tagId);
       List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
       return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post createdPost = postService.createPost(loggedInUser,createPostRequest);
        PostDto createPostDto = postMapper.toDto(createdPost);
        return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto
            ) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post udpdatedPost = postService.updatePost(id, updatePostRequest);
        PostDto updatedPostDto = postMapper.toDto(udpdatedPost);

        return ResponseEntity.ok(updatedPostDto);
    }
}
