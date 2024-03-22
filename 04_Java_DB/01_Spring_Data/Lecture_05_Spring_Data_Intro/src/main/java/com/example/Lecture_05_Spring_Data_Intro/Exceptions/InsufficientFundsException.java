package com.example.Lecture_05_Spring_Data_Intro.Exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}