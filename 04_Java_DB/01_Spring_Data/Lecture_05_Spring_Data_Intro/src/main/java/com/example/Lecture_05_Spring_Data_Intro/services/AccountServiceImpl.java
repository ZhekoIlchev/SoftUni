package com.example.Lecture_05_Spring_Data_Intro.services;

import com.example.Lecture_05_Spring_Data_Intro.Exceptions.AccountNotFoundException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.InsufficientFundsException;
import com.example.Lecture_05_Spring_Data_Intro.models.Account;
import com.example.Lecture_05_Spring_Data_Intro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) throws AccountNotFoundException, InsufficientFundsException {
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("There is no such account with the given id."));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Not enough money");
        }

        account.setBalance(account.getBalance().subtract(amount));

        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) throws AccountNotFoundException {
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("There is no such account with the given id."));

        account.setBalance(account.getBalance().add(amount));

        this.accountRepository.save(account);
    }

    @Override
    @Transactional
    public void transferMoneyFromOneAccountToAnother(Long fromId, Long toId, BigDecimal amount) {
        this.withdrawMoney(amount, fromId);
        this.transferMoney(amount, toId);
    }
}