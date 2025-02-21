package com.fernandobouchet.blog.controllers;

import com.fernandobouchet.blog.domain.dtos.TagResponse;
import com.fernandobouchet.blog.domain.entities.Tag;
import com.fernandobouchet.blog.mappers.TagMapper;
import com.fernandobouchet.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tagMapper.toTagResponseList(tags));
    }
}
