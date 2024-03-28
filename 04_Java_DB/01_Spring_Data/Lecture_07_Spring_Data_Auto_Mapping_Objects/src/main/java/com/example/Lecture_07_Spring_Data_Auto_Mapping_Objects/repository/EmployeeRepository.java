package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.repository;

import com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}