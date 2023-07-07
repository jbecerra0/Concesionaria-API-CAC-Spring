package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface VehicleService {
    // 1 - Añade un nuevo vehiculo a la base de datos
    ResponseVehicleDTO addVehicle(VehicleDTO vehicleDTO);
    // 2 - Retorna todos los vehículos usados
    ResponseVehiclesDTO getAllUsedVehicles();
    // 3 - Retorna una lista de vehículos fabricados entre 2 fechas
    ResponseVehiclesDTO vehiclesSinceTo(LocalDate since, LocalDate to);
    // 4 - Retorna un vehiculo por ID
    ResponseVehicleDTO getVehicleById(Long id);
}
