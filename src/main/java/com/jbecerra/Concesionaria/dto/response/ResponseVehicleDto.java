package com.jbecerra.Concesionaria.dto.response;

import com.jbecerra.Concesionaria.dto.request.RequestVehicleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehicleDto {
    private String message;
    private RequestVehicleDto vehicleDto;
}
