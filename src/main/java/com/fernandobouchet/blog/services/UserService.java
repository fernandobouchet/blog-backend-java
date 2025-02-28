package com.fernandobouchet.blog.services;

import com.fernandobouchet.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
