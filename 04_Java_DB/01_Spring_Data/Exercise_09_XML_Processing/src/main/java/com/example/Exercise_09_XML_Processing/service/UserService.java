package com.example.Exercise_09_XML_Processing.service;

import com.example.Exercise_09_XML_Processing.model.dto.UserSeedDto;
import com.example.Exercise_09_XML_Processing.model.dto.UserWithSoldProductViewRootDto;
import com.example.Exercise_09_XML_Processing.model.entity.User;

import java.util.List;

public interface UserService {
    Long getEntityCount();

    void seedUsers(List<UserSeedDto> users);

    User findRandomUser();

    UserWithSoldProductViewRootDto findAllUsersWhoHaveAtLeastOneSoldProduct();
}