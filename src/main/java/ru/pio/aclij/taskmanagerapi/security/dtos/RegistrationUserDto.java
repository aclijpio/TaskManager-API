package ru.pio.aclij.taskmanagerapi.security.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public RegistrationUserDto(String email, String username, String password, String confirmPassword) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
