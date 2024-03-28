package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.service;

import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto.EmployeeDto;
import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto.ManagerDto;
import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.entities.Employee;
import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = "src/main/resources/Files/employees.txt";
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, @Qualifier("advancedModelMapper") ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public void seedEmployees() throws IOException {
        if (this.employeeRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(EMPLOYEES_FILE_PATH))
                .forEach(row -> {
                    String[] employeeInfo = row.split("\\s+");
                    Employee employee = createEmployee(employeeInfo);
                    this.employeeRepository.save(employee);
                });
    }

    @Override
    public EmployeeDto findEmployeeById(Long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElse(null);
        return this.mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public ManagerDto findManagerById(Long managerId) {
        Employee employee = this.employeeRepository.findById(managerId).orElse(null);
        return this.mapper.map(employee, ManagerDto.class);
    }

    @Override
    public List<ManagerDto> findAllEmployees() {
        List<Employee> allEmployees = this.employeeRepository.findAll();
        return this.mapper.map(allEmployees, new TypeToken<List<ManagerDto>>() {
        }.getType());
    }

    private Employee createEmployee(String[] employeeInfo) {
        String firstName = employeeInfo[0];
        String lastName = employeeInfo[1];

        BigDecimal salary = new BigDecimal(employeeInfo[2]);
        LocalDate birthday = LocalDate.parse(employeeInfo[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String address = Arrays
                .stream(employeeInfo)
                .skip(4)
                .collect(Collectors.joining(" "));

        return new Employee(firstName, lastName, salary, birthday, address);
    }
}