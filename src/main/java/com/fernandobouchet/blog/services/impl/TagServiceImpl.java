package com.fernandobouchet.blog.services.impl;

import com.fernandobouchet.blog.domain.entities.Tag;
import com.fernandobouchet.blog.repositories.TagRepository;
import com.fernandobouchet.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostCount();
    }
}
