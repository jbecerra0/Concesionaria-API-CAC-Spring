package com.jbecerra.Concesionaria.dto.response;

import com.jbecerra.Concesionaria.dto.request.RequestVehicleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiclesDto {
    String message;
    List<RequestVehicleDto> vehiclesDto;
}
