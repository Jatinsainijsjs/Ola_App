package com.SimpleCabProject.SimpleCabProject.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {
    private int customerId;       // Assuming the customer is sending the customer ID
    private String startLocation; // Starting location of the ride
    private String endLocation;   // Ending location of the ride
    private String category;
    private String message;
    // The category the customer selects (Car, Bike, Auto)
//    private double distance;

//    private String startLocation;
//    private String endLocation;

    // getters and setters
}
