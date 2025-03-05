package com.fernandobouchet.blog.services;

import com.fernandobouchet.blog.domain.CreatePostRequest;
import com.fernandobouchet.blog.domain.UpdatePostRequest;
import com.fernandobouchet.blog.domain.entities.Post;
import com.fernandobouchet.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    Post getPost(UUID id);
}
