package com.example.Exercise_08_JSON_Processing.service.impl;

import com.example.Exercise_08_JSON_Processing.model.dto.CategorySeedDto;
import com.example.Exercise_08_JSON_Processing.model.entity.Category;
import com.example.Exercise_08_JSON_Processing.repository.CategoryRepository;
import com.example.Exercise_08_JSON_Processing.service.CategoryService;
import com.example.Exercise_08_JSON_Processing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.RESOURCE_FILE_PATH;
import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.CATEGORIES_FILE_NAME;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() > 0) {
            return;
        }

        String categoriesAsJSON = Files.readString(Path.of(RESOURCE_FILE_PATH + CATEGORIES_FILE_NAME));
        CategorySeedDto[] categories = this.gson.fromJson(categoriesAsJSON, CategorySeedDto[].class);

        for (CategorySeedDto categoryDto : categories) {

            if (this.validationUtil.isValid(categoryDto)) {
                Category category = this.modelMapper.map(categoryDto, Category.class);
                this.categoryRepository.save(category);
            }
        }
    }

    @Override
    public Set<Category> findRandomCategories() {
        int categoryCount = ThreadLocalRandom
                .current().nextInt(1, 3);
        long categoryTotalCount = this.categoryRepository.count();
        Set<Category> categorySet = new HashSet<>();

        for (int i = 0; i < categoryCount; i++) {
            Long randomCategoryId = ThreadLocalRandom
                    .current().nextLong(1, categoryTotalCount + 1);

            categorySet.add(this.categoryRepository.findById(randomCategoryId).orElse(null));
        }

        return categorySet;
    }
}