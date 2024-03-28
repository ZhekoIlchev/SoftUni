package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects;

import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final EmployeeService employeeService;
    private final BufferedReader bufferedReader;

    @Autowired
    public CommandLineRunnerImpl(EmployeeService employeeService, BufferedReader bufferedReader) {
        this.employeeService = employeeService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println("Please select exercise number.");
        int exerciseNumber = Integer.parseInt(this.bufferedReader.readLine());

        switch (exerciseNumber) {
            case 1 -> getEmployeeById();
            case 2 -> getManagerById();
            case 3 -> getAllEmployees();
        }

    }

    private void getAllEmployees() {
        this.employeeService.findAllEmployees()
                .forEach(System.out::println);
    }

    private void getManagerById() throws IOException {
        System.out.println("Please, enter manager ID.");
        Long managerId = Long.parseLong(this.bufferedReader.readLine());

        System.out.println(this.employeeService.findManagerById(managerId));
    }

    private void getEmployeeById() throws IOException {
        System.out.println("Please, enter employee ID.");
        Long employeeId = Long.parseLong(this.bufferedReader.readLine());

        System.out.println(this.employeeService.findEmployeeById(employeeId));
    }

    private void seedData() throws IOException {
        this.employeeService.seedEmployees();
    }
}