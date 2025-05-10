package com.SimpleCabProject.SimpleCabProject.Security;

import com.SimpleCabProject.SimpleCabProject.Entities.Admin;
import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
import com.SimpleCabProject.SimpleCabProject.Entities.LoginRequest;
import com.SimpleCabProject.SimpleCabProject.Repository.AdminRepository;
import com.SimpleCabProject.SimpleCabProject.Repository.CustomerRepository;
import com.SimpleCabProject.SimpleCabProject.dtos.CustomerDTO;
import com.SimpleCabProject.SimpleCabProject.Security.JwtService;
import com.SimpleCabProject.SimpleCabProject.Service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.SimpleCabProject.SimpleCabProject.Security.JwtService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/customer/register")
    public ResponseEntity<Map<String, Object>> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Encrypt the password
            customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

            // Register customer
            Customer savedCustomer = customerService.registerCustomer(customerDTO);

            // Generate JWT token
            String token = jwtService.generateToken(savedCustomer.getEmail(), "customer");

            response.put("message", "Customer registered successfully");
            response.put("customerId", savedCustomer.getId());
            response.put("token", token);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @PostMapping("/customer/login")
    public ResponseEntity<Map<String, Object>> customerLogin(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // Get customer details
            Optional<Customer> customerOpt = customerRepository.findByEmail(loginRequest.getEmail());
            if (!customerOpt.isPresent()) {
                response.put("error", "Customer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            Customer customer = customerOpt.get();

            // Generate JWT token
            String token = jwtService.generateToken(customer.getEmail(), "customer");

            response.put("message", "Login successful");
            response.put("customerId", customer.getId());
            response.put("name", customer.getName());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<Map<String, Object>> adminLogin(@RequestBody Map<String, String> loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Get admin details
            Optional<Admin> adminOpt = adminRepository.findById(username);
            if (!adminOpt.isPresent()) {
                response.put("error", "Admin not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Generate JWT token
            String token = jwtService.generateToken(username, "admin");

            response.put("message", "Login successful");
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Map<String, Object>> createAdmin(@RequestBody Map<String, String> adminRequest) {
        Map<String, Object> response = new HashMap<>();

        String username = adminRequest.get("username");
        String password = adminRequest.get("password");

        // Check if admin already exists
        if (adminRepository.existsById(username)) {
            response.put("error", "Admin with this username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // Create new admin with encoded password
        Admin admin = new Admin(username, passwordEncoder.encode(password));
        adminRepository.save(admin);

        // Generate JWT token
        String token = jwtService.generateToken(username, "admin");

        response.put("message", "Admin created successfully");
        response.put("username", username);
        response.put("token", token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}