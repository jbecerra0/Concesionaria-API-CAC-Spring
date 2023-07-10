package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.RequestVehicleDto;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDto;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface VehicleService {
    // 1 - Añade un nuevo vehiculo a la base de datos
    ResponseVehicleDto addVehicle(RequestVehicleDto vehicleDTO);
    // 2 - Retorna todos los vehículos usados
    ResponseVehiclesDto getAllUsedVehicles();
    // 3 - Retorna una lista de vehículos fabricados entre 2 fechas
    ResponseVehiclesDto vehiclesSinceTo(LocalDate since, LocalDate to);
    // 4 - Retorna un vehiculo por ID
    ResponseVehicleDto getVehicleById(Long id);
}
