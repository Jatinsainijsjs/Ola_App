package com.SimpleCabProject.SimpleCabProject.Service;
//
//import com.SimpleCabProject.SimpleCabProject.Service.impl.DistanceService;
//import com.SimpleCabProject.SimpleCabProject.dtos.OpenRouteResponse;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//
//@Service
//public class DistanceServiceImpl implements DistanceService {
//
//    @Value("${opencage.api.key}")
//    private String opencageApiKey;
//
//    @Value("${openrouteservice.api.key}")
//    private String openrouteApiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    // Optional: Returns just the double distance in km (used if DTO not needed)
//    @Override
//    public double getDistanceInKm(String startLocation, String endLocation) {
//        double[] startCoords = getCoordinates(startLocation);
//        double[] endCoords = getCoordinates(endLocation);
//
//       //  Print the coordinates in a human-readable format
//       System.out.println("Start Coordinates: " + Arrays.toString(startCoords));
//       System.out.println("End Coordinates: " + Arrays.toString(endCoords));
////
//        return getDistanceFromRoute(startCoords, endCoords);
//    }
//
//    @Override
//    public double calculateDistance(String startLocation, String endLocation) {
//        return getDistanceInKm(startLocation, endLocation);
//    }
//
//    private double[] getCoordinates(String location) {
//        String url = "https://api.opencagedata.com/geocode/v1/json?q=" + location + "&key=" + opencageApiKey;
//        try {
//            String response = restTemplate.getForObject(url, String.class);
//            JsonNode jsonNode = objectMapper.readTree(response);
//            JsonNode geometry = jsonNode.path("results").get(0).path("geometry");
//            double lat = geometry.get("lat").asDouble();
//            double lng = geometry.get("lng").asDouble();
//            return new double[]{lat, lng};
//        } catch (Exception e) {
//            throw new RuntimeException("Error getting coordinates for location: " + location, e);
//        }
//    }
//
//    private double getDistanceFromRoute(double[] start, double[] end) {
//        String url = "https://api.openrouteservice.org/v2/directions/driving-car";
//        try {
//            String requestJson = "{\n" +
//                    "  \"coordinates\": [[ " + start[1] + ", " + start[0] + " ], [ " + end[1] + ", " + end[0] + " ]]\n" +
//                    "}";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", openrouteApiKey);
//
//            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//            JsonNode jsonNode = objectMapper.readTree(response.getBody());
//            double meters = jsonNode.path("features").get(0)
//                    .path("properties").path("segments").get(0)
//                    .path("distance").asDouble();
//
//            return meters / 1000.0;
//        } catch (Exception e) {
//            throw new RuntimeException("Error calculating route distance", e);
//        }
//    }
//
//    @Override
//    public double calculateFareFromORSResponse(OpenRouteResponse response, boolean isFirstTimeUser) {
//        OpenRouteResponse.Summary summary = response.getRoutes().get(0).getSummary();
//
//        double distanceInKm = summary.getDistance() / 1000.0;
//        double baseFarePerKm = 10.0;
//        double gstRate = 0.18;
//
//        double fare = distanceInKm * baseFarePerKm;
//
//        // Apply discount
//        if (isFirstTimeUser && fare > 100) {
//            fare *= 0.9; // 10% discount
//        }
//
//        fare += fare * gstRate;
//
//        return Math.round(fare * 100.0) / 100.0;
//    }
//
//    @Override
//    public OpenRouteResponse getRouteResponse(String startLocation, String endLocation) {
//        double[] startCoords = getCoordinates(startLocation);
//        double[] endCoords = getCoordinates(endLocation);
//
//        String url = "https://api.openrouteservice.org/v2/directions/driving-car";
//
//        try {
//            String requestJson = "{\n" +
//                    "  \"coordinates\": [[ " + startCoords[1] + ", " + startCoords[0] + " ], [ " + endCoords[1] + ", " + endCoords[0] + " ]]\n" +
//                    "}";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", openrouteApiKey);
//
//            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//            return objectMapper.readValue(response.getBody(), OpenRouteResponse.class);
//        } catch (Exception e) {
//            throw new RuntimeException("Error retrieving route response from OpenRouteService", e);
//        }
//    }
//}


import com.SimpleCabProject.SimpleCabProject.Service.impl.DistanceService;
import com.SimpleCabProject.SimpleCabProject.dtos.OpenRouteResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;



import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;


@Service
public  class DistanceServiceImpl implements DistanceService {

    @Value("${opencage.api.key}")
    private String opencageApiKey;

    @Value("${openrouteservice.api.key}")
    private String openrouteApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public double getDistanceInKm(String start, String end) {
        return 0;
    }


    @Override
    public double calculateDistance(String startLocation, String endLocation) {
        // Step 1: Get coordinates from OpenCage
        double[] startCoords = getCoordinates(startLocation);
        double[] endCoords = getCoordinates(endLocation);

        // Print the coordinates in a human-readable format
        System.out.println("Start Coordinates: " + Arrays.toString(startCoords));
        System.out.println("End Coordinates: " + Arrays.toString(endCoords));

        // Step 2: Use OpenRouteService to get distance
        return getDistanceFromRoute(startCoords, endCoords);
    }


    private double[] getCoordinates(String location) {
        String url = "https://api.opencagedata.com/geocode/v1/json?q=" + location + "&key=" + opencageApiKey;
        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode geometry = jsonNode.path("results").get(0).path("geometry");
            double lat = geometry.get("lat").asDouble();
            double lng = geometry.get("lng").asDouble();
            return new double[]{lat, lng};
        } catch (Exception e) {
            throw new RuntimeException("Error getting coordinates for location: " + location, e);
        }
    }


    private double getDistanceFromRoute(double[] start, double[] end) {
        String url = "https://api.openrouteservice.org/v2/directions/driving-car";
        try {
            // Create the JSON body for the request (OpenRouteService expects [lon, lat])
            String requestJson = "{\n" +
                    "  \"coordinates\": [[ " + start[1] + ", " + start[0] + " ], [ " + end[1] + ", " + end[0] + " ]]\n" +
                    "}";

            // Set headers and API key for authorization
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", openrouteApiKey);

            // Send the request
            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);


            String json = response.getBody(); // your JSON string

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

// Navigate to distance and duration
            JsonNode summary = root.path("routes").get(0).path("summary");
            double distance = summary.path("distance").asDouble(); // in meters
            double duration = summary.path("duration").asDouble(); // in seconds

            double distanceInKm = distance / 1000.0;
            int roundedDistance = (int) Math.round(distanceInKm);
            System.out.println("Rounded Distance: " + roundedDistance + " km");
            double d = roundedDistance;
//            System.out.println("Distance: " + distance + " meters");
//            System.out.println("Duration: " + duration + " seconds");

//// Extract instructions
//            JsonNode steps = root.path("routes").get(0).path("segments").get(0).path("steps");
//
//            for (JsonNode step : steps) {
//                String instruction = step.path("instruction").asText();
//                double stepDistance = step.path("distance").asDouble();
//                double stepDuration = step.path("duration").asDouble();
//                System.out.println("Instruction: " + instruction);
//                System.out.println("Step Distance: " + stepDistance);
//                System.out.println("Step Duration: " + stepDuration);
//            }

            // Log the full response for debugging
            System.out.println("Response from OpenRouteService: " + response.getBody());

            // Parse the response
//            JsonNode jsonNode = objectMapper.readTree(response.getBody());
//            double meters = jsonNode.path("features").get(0).path("properties").path("segments").get(0).path("distance").asDouble();
            System.out.println("Metres coming ---------------");
//            return meters / 1000.0;
// convert to kilometers
            return d;
        } catch (Exception e) {
            throw new RuntimeException("Error calculating route distance", e);
        }
    }
}
