package com.jbecerra.Concesionaria.dto.response;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiclesDTO {
    String message;
    List<VehicleDTO> vehiclesDTO;
}
