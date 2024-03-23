package com.example.Exercise_05_Spring_Data_Intro.repository;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}