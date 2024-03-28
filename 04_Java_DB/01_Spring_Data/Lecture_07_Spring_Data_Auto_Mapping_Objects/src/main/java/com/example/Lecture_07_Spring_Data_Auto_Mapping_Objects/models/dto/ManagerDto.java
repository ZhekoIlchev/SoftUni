package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ManagerDto extends BasicEmployeeDto {
    private BigDecimal income;
    private boolean isOnVacation;
    private Set<EmployeeDto> subordinates;

    public ManagerDto() {
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public boolean isOnVacation() {
        return isOnVacation;
    }

    public void setOnVacation(boolean onVacation) {
        isOnVacation = onVacation;
    }

    public Set<EmployeeDto> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<EmployeeDto> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        StringBuilder manager = new StringBuilder(String.format("%s %s %.2f. He/She %s and he/she is responsible for: ",
                super.getFirstName(),
                super.getLastName(),
                this.income,
                this.isOnVacation ? "is on holiday." : "isn't on holiday"))
                .append(System.lineSeparator());

        this.getSubordinates()
                .forEach(employeeDto -> manager
                        .append(String.format("   %s", employeeDto.toString()))
                        .append(System.lineSeparator()));

        return manager.toString();
    }
}