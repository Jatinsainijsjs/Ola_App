package com.SimpleCabProject.SimpleCabProject.Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter

public class Admin {

    @Id
    private String username; // e.g. "admin"

    private String password;

    // e.g. "admin123"


    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

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
