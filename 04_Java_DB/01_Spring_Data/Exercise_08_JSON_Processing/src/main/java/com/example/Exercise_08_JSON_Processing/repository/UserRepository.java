package com.example.Exercise_08_JSON_Processing.repository;

import com.example.Exercise_08_JSON_Processing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u " +
            "WHERE (SELECT COUNT(p) FROM Product p WHERE p.seller.id = u.id) > 0 " +
            "ORDER BY u.lastName, u.firstName")
    List<User> findAllUsersWhoHaveAtLeastOneSoldProduct();
}