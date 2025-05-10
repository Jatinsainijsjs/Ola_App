package com.SimpleCabProject.SimpleCabProject.Service.impl;




import com.SimpleCabProject.SimpleCabProject.dtos.OpenRouteResponse;

public interface DistanceService {
    double getDistanceInKm(String startLocation, String endLocation);
    double calculateDistance(String startLocation, String endLocation);
//    double calculateFareFromORSResponse(OpenRouteResponse response, boolean isFirstTimeUser);
//    OpenRouteResponse getRouteResponse(String startLocation, String endLocation);
}

//
//public interface DistanceService {
//    double getDistanceInKm(String start, String end);
//
//    double calculateDistance(String startLocation, String endLocation);
//}