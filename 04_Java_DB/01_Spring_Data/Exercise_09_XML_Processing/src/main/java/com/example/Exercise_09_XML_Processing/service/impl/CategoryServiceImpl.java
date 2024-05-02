package com.example.Exercise_09_XML_Processing.service.impl;

import com.example.Exercise_09_XML_Processing.model.dto.CategorySeedDto;
import com.example.Exercise_09_XML_Processing.model.entity.Category;
import com.example.Exercise_09_XML_Processing.repository.CategoryRepository;
import com.example.Exercise_09_XML_Processing.service.CategoryService;
import com.example.Exercise_09_XML_Processing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long getEntityCount() {
        return this.categoryRepository.count();
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categories) {
        categories
                .stream()
                .filter(this.validationUtil::isValid)
                .map(categorySeedDto -> this.modelMapper.map(categorySeedDto, Category.class))
                .forEach(this.categoryRepository::save);
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