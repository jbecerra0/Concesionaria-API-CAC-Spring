package com.jbecerra.Concesionaria.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestVehicleDto {
    private String brand;
    private String model;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate manufacturingDate;
    private Long numberOfKilometers;
    private String doors;
    private String price;
    private String currency;
    private List<RequestServiceVehicleDto> servicesVehicle;
    private String countOfOwners;
}
