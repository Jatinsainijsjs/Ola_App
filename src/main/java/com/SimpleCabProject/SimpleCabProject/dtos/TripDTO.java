package com.SimpleCabProject.SimpleCabProject.dtos;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private String category;
    private String startLocation;
    private String endLocation;
    private double distance;
    private double fare;
    private double gst;
    private double totalAmount;
    private String vehicle;  // Optional: Model + number
    private String message;
//    private String vehicle;
//    private String message;
//    private String startLocation;
//    private String endLocation;
//    private double distance;
//    private double fare;
//    private double gst;
//    private double totalAmount;
    // getters and setters
}