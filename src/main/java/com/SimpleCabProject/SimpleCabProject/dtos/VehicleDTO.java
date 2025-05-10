package com.SimpleCabProject.SimpleCabProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private int id;
    private String model;
    private String number;
    private String location;
    private boolean available;
    private int categoryId;
    private String categoryName;
}
