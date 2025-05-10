package com.SimpleCabProject.SimpleCabProject;


import com.SimpleCabProject.SimpleCabProject.Entities.Category;
import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
import com.SimpleCabProject.SimpleCabProject.Entities.Vehicle;
import com.SimpleCabProject.SimpleCabProject.Repository.CategoryRepository;
import com.SimpleCabProject.SimpleCabProject.Repository.CustomerRepository;
import com.SimpleCabProject.SimpleCabProject.Repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;
    private final VehicleRepository vehicleRepository;

    public DataLoader(CustomerRepository customerRepository, CategoryRepository categoryRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add Categories
        Category autoCategory = new Category();
        autoCategory.setName("Auto");

        Category bikeCategory = new Category();
        bikeCategory.setName("Bike");

        Category carCategory = new Category();
        carCategory.setName("Car");

        categoryRepository.saveAll(List.of(autoCategory, bikeCategory, carCategory));

        // Add Customers
        List<Customer> customers = Arrays.asList(
                new Customer("Amit Sharma", "amit.sharma@example.com", "password123", true),
                new Customer("Priya Singh", "priya.singh@example.com", "pass456", true),
                new Customer("Ravi Verma", "ravi.verma@example.com", "ravi123", true),
                new Customer("Neha Joshi", "neha.joshi@example.com", "neha456", true),
                new Customer("Karan Mehta", "karan.mehta@example.com", "karan789", true)
        );
        customerRepository.saveAll(customers);

        // Add Vehicles
        List<Vehicle> vehicles = Arrays.asList(
                // Autos
                new Vehicle("Bajaj RE", "DL01AUTO001", "Delhi", true, autoCategory),
                new Vehicle("Piaggio Ape", "DL02AUTO002", "Noida", true, autoCategory),
                new Vehicle("TVS King", "DL03AUTO003", "Gurgaon", true, autoCategory),

                // Bikes
                new Vehicle("Hero Splendor", "DL01BIKE001", "Delhi", true, bikeCategory),
                new Vehicle("Bajaj Pulsar", "DL02BIKE002", "Ghaziabad", true, bikeCategory),
                new Vehicle("TVS Apache", "DL03BIKE003", "Noida", true, bikeCategory),

                // Cars
                new Vehicle("Maruti Swift", "DL01CAR001", "Delhi", true, carCategory),
                new Vehicle("Hyundai i20", "DL02CAR002", "Faridabad", true, carCategory),
                new Vehicle("Honda City", "DL03CAR003", "Gurgaon", true, carCategory),
                new Vehicle("Tata Nexon", "DL04CAR004", "Ghaziabad", true, carCategory)
        );
        vehicleRepository.saveAll(vehicles);
    }
}

//
//import com.SimpleCabProject.SimpleCabProject.Entities.Admin;
//import com.SimpleCabProject.SimpleCabProject.Entities.Category;
//import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
//import com.SimpleCabProject.SimpleCabProject.Entities.Vehicle;
//import com.SimpleCabProject.SimpleCabProject.Repository.CategoryRepository;
//import com.SimpleCabProject.SimpleCabProject.Repository.CustomerRepository;
//import com.SimpleCabProject.SimpleCabProject.Repository.VehicleRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final CustomerRepository customerRepository;
//    private final CategoryRepository categoryRepository;
//    private final VehicleRepository vehicleRepository;
//
//    public DataLoader(CustomerRepository customerRepository, CategoryRepository categoryRepository, VehicleRepository vehicleRepository) {
//        this.customerRepository = customerRepository;
//        this.categoryRepository = categoryRepository;
//        this.vehicleRepository = vehicleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Insert Customers
//        Customer customer1 = new Customer("John Doe", "john@example.com", "password123", true);
//        Customer customer2 = new Customer("Jane Smith", "jane@example.com", "password456", true);
//        customerRepository.saveAll(List.of(customer1, customer2));
//
//        // Insert Categories
//        Category autoCategory = new Category();
//        autoCategory.setName("Auto");
//
//        Category bikeCategory = new Category();
//        bikeCategory.setName("Bike");
//
//        Category carCategory = new Category();
//        carCategory.setName("Car");
//
//        categoryRepository.saveAll(List.of(autoCategory, bikeCategory, carCategory));
//
//        // Insert Vehicles - Auto
//        Vehicle auto1 = new Vehicle("Bajaj RE", "AUTO001", "Delhi", true, autoCategory);
//        Vehicle auto2 = new Vehicle("Piaggio Ape", "AUTO002", "Noida", true, autoCategory);
//
//        // Insert Vehicles - Bike
//        Vehicle bike1 = new Vehicle("Hero Splendor", "BIKE001", "Delhi", true, bikeCategory);
//        Vehicle bike2 = new Vehicle("Bajaj Pulsar", "BIKE002", "Gurgaon", true, bikeCategory);
//
//        // Insert Vehicles - Car
//        Vehicle car1 = new Vehicle("Hyundai i20", "CAR001", "Delhi", true, carCategory);
//        Vehicle car2 = new Vehicle("Maruti Swift", "CAR002", "Noida", true, carCategory);
//        Vehicle car3 = new Vehicle("Honda Amaze", "CAR003", "Gurgaon", true, carCategory);
//
//        vehicleRepository.saveAll(List.of(auto1, auto2, bike1, bike2, car1, car2, car3));
//    }
//}



//import com.SimpleCabProject.SimpleCabProject.Entities.Admin;
//import com.SimpleCabProject.SimpleCabProject.Repository.AdminRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.SimpleCabProject.SimpleCabProject.Entities.Category;
//import com.SimpleCabProject.SimpleCabProject.Entities.Customer;
//import com.SimpleCabProject.SimpleCabProject.Entities.Vehicle;
//import com.SimpleCabProject.SimpleCabProject.Repository.CategoryRepository;
//import com.SimpleCabProject.SimpleCabProject.Repository.CustomerRepository;
//import com.SimpleCabProject.SimpleCabProject.Repository.VehicleRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final CustomerRepository customerRepository;
//    private final CategoryRepository categoryRepository;
//    private final VehicleRepository vehicleRepository;
//
//    public DataLoader(CustomerRepository customerRepository, CategoryRepository categoryRepository, VehicleRepository vehicleRepository) {
//        this.customerRepository = customerRepository;
//        this.categoryRepository = categoryRepository;
//        this.vehicleRepository = vehicleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Insert Customers
//        Customer customer1 = new Customer();
//        customer1.setName("John Doe");
//        customer1.setEmail("john@example.com");
//        customer1.setPassword("password123");
//        customer1.setFirstTime(true);
//
//        Customer customer2 = new Customer();
//        customer2.setName("Jane Smith");
//        customer2.setEmail("jane@example.com");
//        customer2.setPassword("password456");
//        customer2.setFirstTime(true);
//
//        customerRepository.saveAll(List.of(customer1, customer2));
//
//        // Insert Categories
//        Category autocategory = new Category();
//        autocategory.setName("Auto");
//
//        Category bikecategory = new Category();
//        bikecategory.setName("Bike");
//
//        Category carcategory = new Category();
//        carcategory.setName("Car");
//
//
//        categoryRepository.saveAll(List.of(autocategory, bikecategory,carcategory));
//
//        // Insert Vehicles
//        Vehicle vehicle1 = new Vehicle();
//        vehicle1.setModel("Honda City");
//        vehicle1.setNumber("ABC123");
//        vehicle1.setLocation("Delhi");
//        vehicle1.setAvailable(true);
//        vehicle1.setCategory(autocategory);
//
//        Vehicle vehicle2 = new Vehicle();
//        vehicle2.setModel("Toyota Fortuner");
//        vehicle2.setNumber("XYZ456");
//        vehicle2.setLocation("Noida");
//        vehicle2.setAvailable(true);
//        vehicle2.setCategory(bikecategory);
//
//        Vehicle vehicle3 = new Vehicle();
//        vehicle3.setModel("Hero Splendar");
//        vehicle3.setNumber("XYZ456");
//        vehicle3.setLocation("Hrr");
//        vehicle3.setAvailable(true);
//        vehicle3.setCategory(carcategory);
//
//
//        vehicleRepository.saveAll(List.of(vehicle1, vehicle2,vehicle3));
//    }
//}
//

