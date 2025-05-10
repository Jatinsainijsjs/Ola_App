package com.SimpleCabProject.SimpleCabProject.Service;

import com.SimpleCabProject.SimpleCabProject.Exception.ResourceNotFoundException;
import com.SimpleCabProject.SimpleCabProject.Service.impl.AdminService;
import com.SimpleCabProject.SimpleCabProject.dtos.*;
import com.SimpleCabProject.SimpleCabProject.Entities.*;
import com.SimpleCabProject.SimpleCabProject.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO dto) {
        Category category = new Category();
        System.out.println("category name is:::"+dto.getName());
        category.setName(dto.getName());
        Category saved = categoryRepository.save(category);
        return mapToCategoryDTO(saved);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category with ID " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public VehicleDTO addVehicle(VehicleDTO dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category with ID " + dto.getCategoryId() + " not found");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setModel(dto.getModel());
        vehicle.setNumber(dto.getNumber());
        vehicle.setLocation(dto.getLocation());
        vehicle.setAvailable(true);
        vehicle.setCategory(optionalCategory.get());
        Vehicle saved = vehicleRepository.save(vehicle);
        return mapToVehicleDTO(saved);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(this::mapToVehicleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getVehiclesByCategory(String categoryName) {
        Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
        if (!categoryOpt.isPresent()) {
            throw new ResourceNotFoundException("Category with name " + categoryName + " not found");
        }

        List<Vehicle> vehicles = vehicleRepository.findByCategory(categoryOpt.get());
        return vehicles.stream()
                .map(this::mapToVehicleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(int id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicle with ID " + id + " not found");
        }
        vehicleRepository.deleteById(id);
    }

    // ===== Mapping Methods =====
    private CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    private VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setModel(vehicle.getModel());
        dto.setNumber(vehicle.getNumber());
        dto.setLocation(vehicle.getLocation());
        dto.setAvailable(vehicle.isAvailable());
        dto.setCategoryId(vehicle.getCategory().getId());
        dto.setCategoryName(vehicle.getCategory().getName());
        return dto;
    }

    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return modelMapper.map(existingCategory, CategoryDTO.class);
    }

    @Override
    public VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + id));

        Category category = categoryRepository.findById(vehicleDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + vehicleDTO.getCategoryId()));

        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setNumber(vehicleDTO.getNumber());
        vehicle.setLocation(vehicleDTO.getLocation());
        vehicle.setAvailable(vehicleDTO.isAvailable());
        vehicle.setCategory(category);

        vehicleRepository.save(vehicle);
        VehicleDTO updatedDTO = modelMapper.map(vehicle, VehicleDTO.class);
        updatedDTO.setCategoryName(category.getName());
        return updatedDTO;
    }



    @Override
    public void deleteCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category with name '" + name + "' not found"));
        categoryRepository.delete(category);
    }


//
//    @Override
//    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
//
//        category.setName(categoryDTO.getName());
//        categoryRepository.save(category);
//
//        return new CategoryDTO(category.getId(), category.getName());
//    }


}


