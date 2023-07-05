package com.jbecerra.Concesionaria.dto.response;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehicleDTO {
    private String message;
    private VehicleDTO vehicleDTO;
}
