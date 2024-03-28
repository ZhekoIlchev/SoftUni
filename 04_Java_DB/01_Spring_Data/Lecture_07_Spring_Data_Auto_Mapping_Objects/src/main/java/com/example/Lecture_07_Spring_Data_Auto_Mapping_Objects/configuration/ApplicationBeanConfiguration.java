package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.configuration;

import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto.EmployeeDto;
import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto.ManagerDto;
import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public BufferedReader configureBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean(name = "basicModelMapper")
    public ModelMapper basicModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {

            @Override
            protected void configure() {
                map().setIncome(source.getSalary());
            }
        });

        modelMapper.addMappings(new PropertyMap<Employee, ManagerDto>() {

            @Override
            protected void configure() {
                map().setIncome(source.getSalary());
                map().setOnVacation(source.isOnHoliday());
            }
        });

        /**
         * Възможна е конфигурация по два начина, чрез .addMappings(...) или чрез TypeMap<>... като долния пример.
         * TypeMap<Employee, EmployeeDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeDto.class);
         * typeMap.addMappings(mapping -> mapping.map(Employee::getSalary, EmployeeDto::setIncome));
         */

        return modelMapper;
    }

    @Bean(name = "advancedModelMapper")
    public ModelMapper configureAdvancedModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {

            @Override
            protected void configure() {
                map().setIncome(new BigDecimal(5000));
            }
        });

        modelMapper.addMappings(new PropertyMap<Employee, ManagerDto>() {

            @Override
            protected void configure() {
                map().setIncome(new BigDecimal(15000));
                map().setOnVacation(source.isOnHoliday());
            }
        });

        return modelMapper;
    }
}