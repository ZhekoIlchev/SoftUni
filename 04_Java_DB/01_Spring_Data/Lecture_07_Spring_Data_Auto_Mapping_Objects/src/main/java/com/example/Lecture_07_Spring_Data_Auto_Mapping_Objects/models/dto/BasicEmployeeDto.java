package com.example.Lecture_07_Spring_Data_Auto_Mapping_Objects.models.dto;

public abstract class BasicEmployeeDto {
    private String firstName;
    private String lastName;

    public BasicEmployeeDto() {
    }

    protected String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}