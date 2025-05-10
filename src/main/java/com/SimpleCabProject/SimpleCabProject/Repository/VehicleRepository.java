package com.SimpleCabProject.SimpleCabProject.Repository;

import com.SimpleCabProject.SimpleCabProject.Entities.Category;
import com.SimpleCabProject.SimpleCabProject.Entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByCategory(Category category);
    List<Vehicle> findByCategoryAndAvailable(Category category, boolean available);
    List<Vehicle> findByAvailable(boolean available);
    Optional<Vehicle> findByNumber(String vehicleNumber);

    @Query("SELECT v FROM Vehicle v WHERE v.location = :location AND v.available = true")
    List<Vehicle> findAvailableVehiclesInLocation(String location);

    @Query("SELECT v FROM Vehicle v JOIN v.category c WHERE c.name = :categoryName AND v.available = true")
    List<Vehicle> findAvailableVehiclesByCategory(String categoryName);

    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.category.id = :categoryId")
    long countVehiclesByCategory(int categoryId);
}