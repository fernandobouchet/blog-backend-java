package com.fernandobouchet.blog.services;

import com.fernandobouchet.blog.domain.entities.Post;
import com.fernandobouchet.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
}
