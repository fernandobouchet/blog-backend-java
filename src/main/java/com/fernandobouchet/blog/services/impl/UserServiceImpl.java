package com.fernandobouchet.blog.services.impl;

import com.fernandobouchet.blog.domain.entities.User;
import com.fernandobouchet.blog.repositories.UserRepository;
import com.fernandobouchet.blog.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
       return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found with ID " + id));
    }
}
