package com.example.Lecture_05_Spring_Data_Intro;

import com.example.Lecture_05_Spring_Data_Intro.Exceptions.AccountNotFoundException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.InsufficientFundsException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.UserNotFoundException;
import com.example.Lecture_05_Spring_Data_Intro.Exceptions.UsernameAlreadyExistsException;
import com.example.Lecture_05_Spring_Data_Intro.services.AccountService;
import com.example.Lecture_05_Spring_Data_Intro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        String information = "Please chose option from the given commands: " +
                System.lineSeparator() +
                "1. Register new user" +
                System.lineSeparator() +
                "2. Register already exists user" +
                System.lineSeparator() +
                "2. Add new account" +
                System.lineSeparator() +
                "4. Withdraw money" +
                System.lineSeparator() +
                "5. Transfer money" +
                System.lineSeparator() +
                "6. Transfer money from one account to another account";

        System.out.println(information);
        int option = Integer.parseInt(scanner.nextLine());

        try {
            switch (option) {
                case 1 -> {
                    System.out.println("Enter username");
                    String username = scanner.nextLine();

                    System.out.println("Enter age");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.println("enter initial amount");
                    BigDecimal initialAmount = scanner.nextBigDecimal();

                    this.userService.registerUser(username, age, initialAmount);
                }
                case 2 -> this.userService.registerUser("Stoyan", 30, BigDecimal.valueOf(3400));
                case 3 -> this.userService.addAccount(new BigDecimal(5000), 100L);
                case 4 -> this.accountService.withdrawMoney(new BigDecimal(999), 1L);
                case 5 -> this.accountService.transferMoney(new BigDecimal(999), 1L);
                case 6 -> this.accountService.transferMoneyFromOneAccountToAnother(2L, 1L, new BigDecimal(2000));
            }
        } catch (UsernameAlreadyExistsException | UserNotFoundException | AccountNotFoundException |
                 InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}