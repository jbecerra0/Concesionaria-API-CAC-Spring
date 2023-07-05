package com.jbecerra.Concesionaria.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private String brand;
    private String model;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate manufacturingDate;
    private Long numberOfKilometers;
    private String doors;
    private String price;
    private String currency;
    private ArrayList<ServiceVehicleDTO> services;
    private String countOfOwners;
}