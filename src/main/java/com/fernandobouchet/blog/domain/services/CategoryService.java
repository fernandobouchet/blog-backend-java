package com.fernandobouchet.blog.domain.services;

import com.fernandobouchet.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();

    Category createCategory(Category category);
}
