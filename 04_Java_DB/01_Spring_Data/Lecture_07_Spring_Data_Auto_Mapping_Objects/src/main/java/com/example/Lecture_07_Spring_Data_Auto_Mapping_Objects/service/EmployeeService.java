package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.service;

import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto.EmployeeDto;
import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto.ManagerDto;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    void seedEmployees() throws IOException;

    EmployeeDto findEmployeeById(Long employeeId);

    ManagerDto findManagerById(Long managerId);

    List<ManagerDto> findAllEmployees();
}