package com.SimpleCabProject.SimpleCabProject.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Changed to IDENTITY for auto-increment in MySQL
    @Column(name = "category_id")
    private int id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
    // getters and setters


    public Category() {
    }

    public Category(int id, String name, List<Vehicle> vehicles) {
        this.id = id;
        this.name = name;
        this.vehicles = vehicles;
    }
}