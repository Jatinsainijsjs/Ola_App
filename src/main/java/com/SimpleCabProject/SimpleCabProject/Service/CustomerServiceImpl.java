package com.SimpleCabProject.SimpleCabProject.Service;


import com.SimpleCabProject.SimpleCabProject.Security.JwtService;
import com.SimpleCabProject.SimpleCabProject.Service.impl.CustomerService;
import com.SimpleCabProject.SimpleCabProject.dtos.*;
import com.SimpleCabProject.SimpleCabProject.Entities.*;
import com.SimpleCabProject.SimpleCabProject.Repository.*;
import com.SimpleCabProject.SimpleCabProject.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DistanceServiceImpl distanceService;

    @Autowired
    private CategoryRepository categoryRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Customer registerCustomer(CustomerDTO customerDTO) {
        // Check if customer with email already exists
        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
//            return "Customer with this email already exists!";
            throw new RuntimeException("Customer with this email already exists");
        }

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstTime(true);  // Set first-time flag for new customers
        return  customerRepository.save(customer);
//        return "Customer registered successfully!";
    }



    @Override
    public TripDTO bookTrip(TripRequest tripRequest) {
        // 1. Validate customer
        Customer customer = customerRepository.findById(tripRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // 2. Validate category
        Category category = categoryRepository.findByName(tripRequest.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // 3. Get available vehicles
        List<Vehicle> availableVehicles = vehicleRepository.findByCategoryAndAvailable(category, true);
        if (availableVehicles.isEmpty()) {
            throw new ResourceNotFoundException("No available vehicles in this category");
        }

        // 4. Pick a random vehicle
        Vehicle selectedVehicle = availableVehicles.get((int) (Math.random() * availableVehicles.size()));
        selectedVehicle.setAvailable(false);
        vehicleRepository.save(selectedVehicle);

//        double distance = distanceService.calculateDistance(tripRequest.getStartLocation(), tripRequest.getEndLocation());
        double distance = distanceService.calculateDistance(tripRequest.getStartLocation(), tripRequest.getEndLocation());
        System.out.println("Distance (km): " + distance);

        // 6. Calculate fare and GST
        double farePerKm = 10.0; // Example rate per km
        double fare = distance * farePerKm;
        double gst = fare * 0.18;
        double totalAmount = fare + gst;

        String message = "Trip booked successfully";

        // 7. Apply 20% discount if first time and amount > 100
        if (customer.isFirstTime() && totalAmount > 100) {
            double discount = totalAmount * 0.20;
            totalAmount -= discount;
            customer.setFirstTime(false);  // Mark the customer as not first-time
            customerRepository.save(customer);
            message = "You are a first-time user! A 20% discount has been applied.";
        }


        // 8. Save the trip information
        TripDetails trip = new TripDetails();
        trip.setCustomer(customer);
        trip.setVehicle(selectedVehicle);
        trip.setStartLocation(tripRequest.getStartLocation());
        trip.setEndLocation(tripRequest.getEndLocation());
        trip.setCategory(category.getName());
        trip.setFare(fare);
        trip.setGst(gst);
        trip.setTotalAmount(totalAmount);
        trip.setMessage(message);

        tripRepository.save(trip);

        // 9. Return TripDTO with fare details
        TripDTO dto = new TripDTO();
        dto.setCategory(category.getName());
        dto.setStartLocation(tripRequest.getStartLocation());
        dto.setEndLocation(tripRequest.getEndLocation());
        dto.setDistance(distance);
        dto.setFare(fare);
        dto.setGst(gst);
        dto.setTotalAmount(totalAmount);
        dto.setVehicle(selectedVehicle.getModel() + " (" + selectedVehicle.getNumber() + ")");
        dto.setMessage(message);

        return dto;
    }

    @Override
    public List<TripDTO> getTrips(int customerId) {
        // Logic for retrieving the customer's trip history
        List<TripDetails> trips = tripRepository.findByCustomerId(customerId);
        return trips.stream().map(trip -> {
            TripDTO dto = new TripDTO();
            dto.setCategory(trip.getCategory());
            dto.setStartLocation(trip.getStartLocation());
            dto.setEndLocation(trip.getEndLocation());
            dto.setDistance(trip.getDistance());
            dto.setFare(trip.getFare());
            dto.setGst(trip.getGst());
            dto.setTotalAmount(trip.getTotalAmount());
            dto.setVehicle(trip.getVehicle().getModel() + " (" + trip.getVehicle().getNumber() + ")");
            dto.setMessage(trip.getMessage());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public LoginResponseDTO login(String email, String password) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        Optional<Customer> customerOpt = customerRepository.findByEmail(email);
        if (!customerOpt.isPresent()) {
            throw new ResourceNotFoundException("Customer not found");
        }

        Customer customer = customerOpt.get();
        String token = jwtService.generateToken(email, "customer");

        LoginResponseDTO response = new LoginResponseDTO();
        response.setMessage("Login successful");
        response.setCustomerId(customer.getId());
        response.setName(customer.getName());

        return response;
    }
}
