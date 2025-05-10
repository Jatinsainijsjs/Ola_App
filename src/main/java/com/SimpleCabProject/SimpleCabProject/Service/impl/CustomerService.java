package com.SimpleCabProject.SimpleCabProject.Service.impl;

import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
import com.SimpleCabProject.SimpleCabProject.dtos.CustomerDTO;
import com.SimpleCabProject.SimpleCabProject.dtos.LoginResponseDTO;
import com.SimpleCabProject.SimpleCabProject.dtos.TripDTO;
import com.SimpleCabProject.SimpleCabProject.dtos.TripRequest;

import java.util.List;

public interface CustomerService {
//    String registerCustomer(CustomerDTO customerDTO);
Customer registerCustomer(CustomerDTO customerDTO);
    TripDTO bookTrip(TripRequest request);
    List<TripDTO> getTrips(int customerId);

    LoginResponseDTO login(String email, String password);
}
