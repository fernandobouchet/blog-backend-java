package com.fernandobouchet.blog.services;

import com.fernandobouchet.blog.domain.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
}
