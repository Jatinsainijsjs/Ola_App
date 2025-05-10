package com.SimpleCabProject.SimpleCabProject.Service.impl;

import com.SimpleCabProject.SimpleCabProject.dtos.CategoryDTO;
import com.SimpleCabProject.SimpleCabProject.dtos.VehicleDTO;

import java.util.List;

public interface AdminService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(int id);
    VehicleDTO addVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicles();
    List<VehicleDTO> getVehiclesByCategory(String category);
    void deleteVehicle(int id);
    CategoryDTO updateCategory(int id, CategoryDTO categoryDTO);
    VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO);

    void deleteCategoryByName(String name);


}