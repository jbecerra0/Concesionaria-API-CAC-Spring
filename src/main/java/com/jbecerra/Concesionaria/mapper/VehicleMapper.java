package com.jbecerra.Concesionaria.mapper;

import com.jbecerra.Concesionaria.dto.request.RequestVehicleDto;
import com.jbecerra.Concesionaria.entity.Vehicle;
import org.modelmapper.ModelMapper;

import java.util.List;

public class VehicleMapper {
    private static final ModelMapper mapper = new ModelMapper();
    public static Vehicle convertDtoToVehicle(RequestVehicleDto vehicleDTO) {
        return mapper.map(vehicleDTO, Vehicle.class);
    }

    public static RequestVehicleDto convertVehicleToDto(Vehicle vehicle) {
        return mapper.map(vehicle, RequestVehicleDto.class);
    }

    public static List<RequestVehicleDto> convertVehiclesToDtos(List<Vehicle> vehicles) {
        return vehicles
                .stream()
                .map(VehicleMapper::convertVehicleToDto)
                .toList();
    }
}
