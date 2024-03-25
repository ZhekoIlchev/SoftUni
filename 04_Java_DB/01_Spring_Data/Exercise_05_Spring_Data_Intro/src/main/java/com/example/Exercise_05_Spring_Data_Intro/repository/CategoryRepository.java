package com.example.Exercise_05_Spring_Data_Intro.repository;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}