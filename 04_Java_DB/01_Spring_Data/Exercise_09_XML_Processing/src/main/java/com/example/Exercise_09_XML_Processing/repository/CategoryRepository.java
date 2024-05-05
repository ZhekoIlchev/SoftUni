package com.example.Exercise_09_XML_Processing.repository;

import com.example.Exercise_09_XML_Processing.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}