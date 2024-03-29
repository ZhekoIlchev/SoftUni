package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UserLoginDto {

    @Email(message = "Invalid email.")
    private String email;
    @Pattern(regexp = "[A-Za-z\\d]{6,}", message = "Invalid password.")
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}