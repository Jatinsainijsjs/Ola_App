package com.SimpleCabProject.SimpleCabProject.Repository;

import com.SimpleCabProject.SimpleCabProject.Entities.TripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripDetails, Integer> {
    List<TripDetails> findByCustomerId(int customerId);
//
//    @Query("SELECT SUM(t.fare) FROM TripDetails t WHERE t.customer.id = :customerId")
//    Double getTotalFareByCustomer(@Param("customerId") int customerId);
//
//    @Query("SELECT t FROM TripDetails t WHERE t.vehicle.id = :vehicleId")
//    List<TripDetails> findByVehicleId(int vehicleId);
//
//    @Query("SELECT SUM(t.fare) FROM TripDetails t WHERE t.customer.id = :customerId")
//    Double getTotalFareByCustomer(int customerId);
//
//    @Query("SELECT t FROM TripDetails t ORDER BY t.id DESC")
//    List<TripDetails> findRecentTrips();
//
//    @Query("SELECT t FROM TripDetails t WHERE t.customer.id = :customerId ORDER BY t.id DESC")
//    List<TripDetails> findRecentTripsByCustomer(int customerId);
//
//    @Query("SELECT COUNT(t) FROM TripDetails t WHERE t.startLocation = :location OR t.endLocation = :location")
//    long countTripsByLocation(String location);
}