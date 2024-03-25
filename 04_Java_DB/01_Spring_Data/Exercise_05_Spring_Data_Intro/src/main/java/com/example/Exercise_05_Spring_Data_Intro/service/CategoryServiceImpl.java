package com.example.Exercise_05_Spring_Data_Intro.service;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Category;
import com.example.Exercise_05_Spring_Data_Intro.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.txt";
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(categoryName -> {
                    Category category = new Category(categoryName);
                    this.categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randomInt = ThreadLocalRandom.current().nextInt(1, 3);
        Long categoryCount = this.categoryRepository.count();

        for (int i = 0; i < randomInt; i++) {
            Long categoryId = ThreadLocalRandom.current().nextLong(1, categoryCount + 1);
            Category category = this.categoryRepository.findById(categoryId).orElse(null);
            categories.add(category);
        }

        return categories;
    }
}