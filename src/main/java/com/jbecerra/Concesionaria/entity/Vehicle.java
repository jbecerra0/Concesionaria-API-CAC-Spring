package com.jbecerra.Concesionaria.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String id;
    private String brand;
    private String model;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate manufacturingDate;
    private Long numberOfKilometers;
    private String doors;
    private String price;
    private String currency;
    private ArrayList<ServiceVehicle> serviceVehicles;
    private String countOfOwners;
}
