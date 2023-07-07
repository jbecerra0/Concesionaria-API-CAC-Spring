package com.jbecerra.Concesionaria.mapper;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.entity.Vehicle;
import org.modelmapper.ModelMapper;

import java.util.List;

public class VehicleMapper {
    private static final ModelMapper mapper = new ModelMapper();
    public static Vehicle convertDTOToEntity(VehicleDTO vehicleDTO) {
        return mapper.map(vehicleDTO, Vehicle.class);
    }

    public static VehicleDTO convertEntityToDTO(Vehicle vehicle) {
        return mapper.map(vehicle, VehicleDTO.class);
    }

    public static List<Vehicle> convertDTOSToEntities(List<VehicleDTO> vehicleDTOS) {
        return vehicleDTOS
                .stream()
                .map(VehicleMapper::convertDTOToEntity)
                .toList();
    }

    public static List<VehicleDTO> convertEntitiesToDTOS(List<Vehicle> vehicles) {
        return vehicles
                .stream()
                .map(VehicleMapper::convertEntityToDTO)
                .toList();
    }
}
