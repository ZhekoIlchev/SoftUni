package com.example.Exercise_08_JSON_Processing.service;

import com.example.Exercise_08_JSON_Processing.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();
}