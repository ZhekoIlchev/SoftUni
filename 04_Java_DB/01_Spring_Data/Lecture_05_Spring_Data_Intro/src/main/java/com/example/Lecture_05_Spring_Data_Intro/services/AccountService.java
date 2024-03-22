package com.example.Lecture_05_Spring_Data_Intro.services;

import com.example.Lecture_05_Spring_Data_Intro.Exceptions.AccountNotFoundException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.InsufficientFundsException;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal amount, Long id) throws AccountNotFoundException, InsufficientFundsException;

    void transferMoney(BigDecimal amount, Long id) throws AccountNotFoundException;

    void transferMoneyFromOneAccountToAnother(Long fromId, Long toId, BigDecimal amount);
}