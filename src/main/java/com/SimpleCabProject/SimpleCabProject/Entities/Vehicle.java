package com.SimpleCabProject.SimpleCabProject.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;
    private String number;
    private String location;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Vehicle(String bajajRe, String dl01AUTO001, String delhi, boolean b, Category autoCategory) {
              model=bajajRe;
              number=dl01AUTO001;
              location=delhi;
              available=b;
              category=autoCategory;
    }

//    public Vehicle(int id, boolean available, String location, String number, String model) {
//        this.id = id;
//        this.available = available;
//        this.location = location;
//        this.number = number;
//        this.model = model;
//    }


}
