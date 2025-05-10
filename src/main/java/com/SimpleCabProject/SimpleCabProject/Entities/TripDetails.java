package com.SimpleCabProject.SimpleCabProject.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TripDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    private int customerId;
    private String category;
    private String startLocation;
    private String endLocation;
  // private String startLocation;
//   private String endLocation;
   private double distance;
    private double fare;
   private String message;
   private double gst;
   private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private Vehicle vehicle;
    // getters and setters
}