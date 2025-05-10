package com.SimpleCabProject.SimpleCabProject.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDTO {
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
