package com.example.Exercise_08_JSON_Processing.service.impl;

import com.example.Exercise_08_JSON_Processing.model.dto.UserSeedDto;
import com.example.Exercise_08_JSON_Processing.model.dto.UserWithSoldProductDto;
import com.example.Exercise_08_JSON_Processing.model.entity.User;
import com.example.Exercise_08_JSON_Processing.repository.UserRepository;
import com.example.Exercise_08_JSON_Processing.service.UserService;
import com.example.Exercise_08_JSON_Processing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.RESOURCE_FILE_PATH;
import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.USERS_FILE_NAME;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() > 0) {
            return;
        }

        String usersAsJSON = Files.readString(Path.of(RESOURCE_FILE_PATH + USERS_FILE_NAME));
        UserSeedDto[] users = this.gson.fromJson(usersAsJSON, UserSeedDto[].class);

        Arrays
                .stream(users)
                .filter(this.validationUtil::isValid)
                .map(userDto -> this.modelMapper.map(userDto, User.class))
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
    public List<UserWithSoldProductDto> findAllUsersWhoHaveAtLeastOneSoldProduct() {

        return this.userRepository.findAllUsersWhoHaveAtLeastOneSoldProduct()
                .stream()
                .map(user -> this.modelMapper.map(user, UserWithSoldProductDto.class))
                .collect(Collectors.toList());
    }
}