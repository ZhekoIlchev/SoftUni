package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.repository;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}