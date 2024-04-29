package com.example.Exercise_09_XML_Processing.service;

import com.example.Exercise_09_XML_Processing.model.dto.CategorySeedDto;
import com.example.Exercise_09_XML_Processing.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Long getEntityCount();

    void seedCategories(List<CategorySeedDto> categories);

    Set<Category> findRandomCategories();
}