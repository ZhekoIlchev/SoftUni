package com.example.Lecture_05_Spring_Data_Intro.repositories;

import com.example.Lecture_05_Spring_Data_Intro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}