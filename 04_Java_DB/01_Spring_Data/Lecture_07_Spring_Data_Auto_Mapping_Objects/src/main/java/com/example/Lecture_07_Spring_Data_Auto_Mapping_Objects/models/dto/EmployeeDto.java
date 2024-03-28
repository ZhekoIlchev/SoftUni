package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto;

import java.math.BigDecimal;

public class EmployeeDto extends BasicEmployeeDto {
    private BigDecimal income;

    public EmployeeDto() {
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal salary) {
        this.income = salary;
    }

    @Override
    public String toString() {
        return String.format("Employee: %s %s has salary - %.2f $.",
                super.getFirstName(), super.getLastName(), this.getIncome());
    }
}