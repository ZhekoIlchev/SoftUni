package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service.impl;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.UserLoginDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.UserRegisterDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.entity.User;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.repository.UserRepository;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service.UserService;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong conform password.");

            return;
        }

        if (!this.validateDto(userRegisterDto)) {
            return;
        }

        User user = this.modelMapper.map(userRegisterDto, User.class);

        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        } else {
            user.setAdmin(false);
        }

        this.userRepository.save(user);
        System.out.printf("%s was registered.%n", userRegisterDto.getFullName());
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        if (!this.validateDto(userLoginDto)) {
            return;
        }

        Optional<User> user = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user.isEmpty()) {
            System.out.println("Incorrect username / password");
            return;
        }

        this.loggedInUser = user.get();
        System.out.printf("Successfully logged in %s.%n", user.get().getFullName());
    }

    @Override
    public void logout() {
        if (this.loggedInUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            System.out.printf("User %s successfully logged out.%n", this.loggedInUser.getFullName());
            this.loggedInUser = null;
        }
    }

    private <E> boolean validateDto(E dto) {
        Set<ConstraintViolation<E>> violations = this.validationUtil.violation(dto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return false;
        }

        return true;
    }
}