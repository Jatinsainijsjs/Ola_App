package com.SimpleCabProject.SimpleCabProject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_time", nullable = false)
    private boolean firstTime = true;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private String email;
//    private String password;
//    @Column(name = "first_time", nullable = false)
//    private boolean firstTime = true;

    @OneToMany(mappedBy = "customer")
    private List<TripDetails> trips;

    public Customer(String name, String mail, String password123, boolean b) {

        this.name=name;
        this.email=mail;
        this.password=password123;
        this.firstTime=b;
    }

//    public Customer(String johnDoe, String mail, String password123, boolean b) {
//    }



    // getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public List<TripDetails> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDetails> trips) {
        this.trips = trips;
    }
}