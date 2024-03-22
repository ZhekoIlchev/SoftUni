package com.example.Lecture_05_Spring_Data_Intro.Exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}