package com.example.Lecture_05_Spring_Data_Intro.services;

import com.example.Lecture_05_Spring_Data_Intro.Exceptions.UserNotFoundException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.UsernameAlreadyExistsException;

import java.math.BigDecimal;

public interface UserService {
    void registerUser(String username, int age, BigDecimal initialAmount) throws UsernameAlreadyExistsException;

    void addAccount(BigDecimal amount, Long id) throws UserNotFoundException;
}