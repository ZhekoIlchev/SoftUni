package com.example.Exercise_09_XML_Processing.service.impl;

import com.example.Exercise_09_XML_Processing.model.dto.UserSeedDto;
import com.example.Exercise_09_XML_Processing.model.dto.UserWithSoldProductViewDto;
import com.example.Exercise_09_XML_Processing.model.dto.UserWithSoldProductViewRootDto;
import com.example.Exercise_09_XML_Processing.model.entity.User;
import com.example.Exercise_09_XML_Processing.repository.UserRepository;
import com.example.Exercise_09_XML_Processing.service.UserService;
import com.example.Exercise_09_XML_Processing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long getEntityCount() {
        return this.userRepository.count();
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users
                .stream()
                .filter(this.validationUtil::isValid)
                .map(userSeedDto -> this.modelMapper.map(userSeedDto, User.class))
                .forEach(this.userRepository::save);
    }

    @Override
    public User findRandomUser() {
        Long randomUserId = ThreadLocalRandom
                .current()
                .nextLong(1, this.userRepository.count() + 1);

        return this.userRepository.findById(randomUserId)
                .orElse(null);
    }

    @Override
    public UserWithSoldProductViewRootDto findAllUsersWhoHaveAtLeastOneSoldProduct() {
        UserWithSoldProductViewRootDto usersRootDto = new UserWithSoldProductViewRootDto();

        usersRootDto.setUsers(this.userRepository.findAllUsersWhoHaveAtLeastOneSoldProduct()
                .stream()
                .map(user -> this.modelMapper.map(user, UserWithSoldProductViewDto.class))
                .collect(Collectors.toList()));

        return usersRootDto;
    }
}