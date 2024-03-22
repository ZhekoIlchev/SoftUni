package com.example.Lecture_05_Spring_Data_Intro.services;

import com.example.Lecture_05_Spring_Data_Intro.Exceptions.UserNotFoundException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.UsernameAlreadyExistsException;
import com.example.Lecture_05_Spring_Data_Intro.models.Account;
import com.example.Lecture_05_Spring_Data_Intro.models.User;
import com.example.Lecture_05_Spring_Data_Intro.repositories.AccountRepository;
import com.example.Lecture_05_Spring_Data_Intro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(String username, int age, BigDecimal initialAmount) throws UsernameAlreadyExistsException {

        if (!this.userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setAge(age);

            this.userRepository.save(user);

            Account account = new Account();
            account.setBalance(initialAmount);
            account.setUser(user);

            this.accountRepository.save(account);
        } else {
            throw new UsernameAlreadyExistsException("User with the given username already exists!");
        }
    }

    @Override
    public void addAccount(BigDecimal amount, Long id) throws UserNotFoundException {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("There is no such user with the given id."));

        Account newAccount = new Account();
        newAccount.setBalance(amount);
        newAccount.setUser(user);

        this.accountRepository.save(newAccount);
    }
}