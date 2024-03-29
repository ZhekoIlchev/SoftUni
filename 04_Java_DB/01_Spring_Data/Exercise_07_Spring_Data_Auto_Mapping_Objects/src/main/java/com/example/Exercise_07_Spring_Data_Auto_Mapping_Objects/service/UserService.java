package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.UserLoginDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();
}