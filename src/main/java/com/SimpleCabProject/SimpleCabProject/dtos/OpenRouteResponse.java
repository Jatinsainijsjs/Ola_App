package com.SimpleCabProject.SimpleCabProject.dtos;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenRouteResponse {
    private List<Route> routes;

    @Data
    public static class Route {
        private Summary summary;
    }

    @Data
    public static class Summary {
        private double distance; // in meters
        private double duration; // in seconds
    }
}
