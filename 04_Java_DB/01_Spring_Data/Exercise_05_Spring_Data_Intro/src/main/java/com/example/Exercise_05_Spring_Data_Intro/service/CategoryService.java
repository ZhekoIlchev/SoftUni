package com.example.Exercise_05_Spring_Data_Intro.service;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}