package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.GameAddDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.UserLoginDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.UserRegisterDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service.GameService;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private final BufferedReader bufferedReader;

    @Autowired
    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Please enter your command.");
            String[] command = this.bufferedReader.readLine().split("\\|");

            switch (command[0]) {
                case "RegisterUser" -> this.userService.registerUser(
                        new UserRegisterDto(command[1], command[2], command[3], command[4]));
                case "LoginUser" -> this.userService.loginUser(new UserLoginDto(command[1], command[2]));
                case "Logout" -> this.userService.logout();
                case "AddGame" ->
                        this.gameService.addGame(new GameAddDto(command[1], new BigDecimal(command[2]), Double.parseDouble(command[3]), command[4],
                                command[5], command[6], command[7]));
                case "Stop" -> {
                    return;
                }
            }
        }
    }
}