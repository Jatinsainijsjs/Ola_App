package com.SimpleCabProject.SimpleCabProject.Controller;


import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
import com.SimpleCabProject.SimpleCabProject.Entities.LoginRequest;
import com.SimpleCabProject.SimpleCabProject.Service.CustomerServiceImpl;
import com.SimpleCabProject.SimpleCabProject.dtos.*;
import com.SimpleCabProject.SimpleCabProject.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*") // Enable CORS for frontend integration
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            Customer savedCustomer = customerService.registerCustomer(customerDTO);
            response.put("message", "Customer registered successfully");
            response.put("customerId", savedCustomer.getId()); // Send ID in response
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

//
//    // Register a new customer
//    @PostMapping("/register")
//    public ResponseEntity<Map<String, String>> registerCustomer(@RequestBody CustomerDTO customerDTO) {
//        String result = customerService.registerCustomer(customerDTO);
//        Map<String, String> response = new HashMap<>();
//
//        if (result.contains("already exists")) {
//            response.put("error", result) ;
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//        } else {
//            response.put("message", result);
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//    }

    // Book a trip for a customer
    @PostMapping("/book-trip")
    public ResponseEntity<?> bookTrip(@RequestBody TripRequest tripRequest) {
        try {
            TripDTO tripDTO = customerService.bookTrip(tripRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tripDTO);
        } catch (ResourceNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to book trip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get all trips for a customer by their ID
    @GetMapping("/trips/{customerId}")
    public ResponseEntity<?> getTrips(@PathVariable int customerId) {
        try {
            List<TripDTO> trips = customerService.getTrips(customerId);
            return ResponseEntity.ok(trips);
        } catch (ResourceNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Login endpoint for customers
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponseDTO response = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}

//
//import com.SimpleCabProject.SimpleCabProject.dtos.*;
//import com.SimpleCabProject.SimpleCabProject.Service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/customer")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    // Register a new customer
//    @PostMapping("/register")
//    public String registerCustomer(@RequestBody CustomerDTO customerDTO) {
//        return customerService.registerCustomer(customerDTO);
//    }
//
//    // Book a trip for a customer
//    @PostMapping("/book-trip")
//    public TripDTO bookTrip(@RequestBody TripRequest tripRequest) {
//        return customerService.bookTrip(tripRequest);
//    }
//
//    // Get all trips for a customer by their ID
//    @GetMapping("/trips/{customerId}")
//    public List<TripDTO> getTrips(@PathVariable int customerId) {
//        return customerService.getTrips(customerId);
//    }
//}
