package com.SimpleCabProject.SimpleCabProject.Controller;



import com.SimpleCabProject.SimpleCabProject.Service.AdminServiceImpl;
import com.SimpleCabProject.SimpleCabProject.dtos.CategoryDTO;
import com.SimpleCabProject.SimpleCabProject.dtos.VehicleDTO;
import com.SimpleCabProject.SimpleCabProject.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // Enable CORS for frontend integration
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    // Add a new category
    @PostMapping("/add/categories")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        System.out.println("category name is:::"+categoryDTO.getName());

        CategoryDTO newCategory = adminService.addCategory(categoryDTO);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    // Get all categories
    @GetMapping("getAll/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = adminService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Delete a category by ID
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable int id) {
        try {
            adminService.deleteCategory(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Category with ID " + id + " deleted successfully");
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

  /// Delete by Name
    @DeleteMapping("/categories/name/{name}")
    public ResponseEntity<Map<String, String>> deleteCategoryByName(@PathVariable String name) {
        try {
            adminService.deleteCategoryByName(name);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Category with name '" + name + "' deleted successfully");
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Add a new vehicle
    @PostMapping("add/vehicles")
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO newVehicle = adminService.addVehicle(vehicleDTO);
            return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get all vehicles
    @GetMapping("getAll/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = adminService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Get vehicles by category
    @GetMapping("/vehicles/category/{categoryName}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByCategory(@PathVariable String categoryName) {
        try {
            List<VehicleDTO> vehicles = adminService.getVehiclesByCategory(categoryName);
            return ResponseEntity.ok(vehicles);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a vehicle by ID
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable int id) {
        try {
            adminService.deleteVehicle(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vehicle with ID " + id + " deleted successfully");
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = adminService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = adminService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.ok(updatedVehicle);
    }

}