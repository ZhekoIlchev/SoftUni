package com.example.Exercise_08_JSON_Processing.service;

import com.example.Exercise_08_JSON_Processing.model.dto.UserWithSoldProductDto;
import com.example.Exercise_08_JSON_Processing.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserWithSoldProductDto> findAllUsersWhoHaveAtLeastOneSoldProduct();
}