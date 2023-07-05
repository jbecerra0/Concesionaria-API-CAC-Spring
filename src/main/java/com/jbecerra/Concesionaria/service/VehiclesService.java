package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.request.ServiceVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.entity.Vehicle;
import com.jbecerra.Concesionaria.entity.ServiceVehicle;
import com.jbecerra.Concesionaria.exceptions.VehicleNotFoundException;
import com.jbecerra.Concesionaria.repository.VehiclesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehiclesService {
    final private VehiclesRepository vehiclesRepository;

    public VehiclesService(VehiclesRepository vehiclesRepository) {
        this.vehiclesRepository = vehiclesRepository;
    }

    // 1 - Añade un nuevo vehiculo a la lista
    public ResponseVehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        Vehicle newVehicle = new Vehicle(
                UUID.randomUUID().toString(),
                vehicleDTO.getBrand(),
                vehicleDTO.getModel(),
                vehicleDTO.getManufacturingDate(),
                vehicleDTO.getNumberOfKilometers(),
                vehicleDTO.getDoors(),
                vehicleDTO.getPrice(),
                vehicleDTO.getCurrency(),
                new ArrayList<>(
                        vehicleDTO.getServices()
                        .stream()
                        .map(service -> new ServiceVehicle(service.getDate(), service.getKilometers(), service.getDescriptions()))
                        .toList())
                ,
                vehicleDTO.getCountOfOwners());

        if (vehiclesRepository.addVehicle(newVehicle)) {
            return new ResponseVehicleDTO("Se creo el vehiculo correctamente!", vehicleDTO);
        }
        return new ResponseVehicleDTO("No se pudo crear el vehiculo", vehicleDTO);
    }

    // 2 - Retorna todos los vehículos usados
    public ResponseVehiclesDTO getAllUsedVehicles() {
        List<Vehicle> vehicleEntities = vehiclesRepository.allVehicles();

        List<VehicleDTO> vehicleDTOS = vehicleEntities
                .stream()
                .map(vehicle -> new VehicleDTO(
                                vehicle.getBrand(),
                                vehicle.getModel(),
                                vehicle.getManufacturingDate(),
                                vehicle.getNumberOfKilometers(),
                                vehicle.getDoors(),
                                vehicle.getPrice(),
                                vehicle.getCurrency(),
                                new ArrayList<>(
                                        vehicle.getServiceVehicles()
                                                .stream()
                                                .map(
                                                        service -> new ServiceVehicleDTO(
                                                                service.getDate(),
                                                                service.getKilometers(),
                                                                service.getDescriptions())
                                                )
                                                .toList()),
                                vehicle.getCountOfOwners()
                        )
                ).toList();

        return new ResponseVehiclesDTO(
                 "Vehículos usados en la lista",
                vehicleDTOS.stream()
                        .filter(vehicle -> Integer.parseInt(vehicle.getCountOfOwners()) > 0)
                        .toList());
    }

    // 3 - Retorna una lista de vehículos fabricados entre 2 fechas
    public ResponseVehiclesDTO vehiclesSinceTo(LocalDate since, LocalDate to) {
        List<Vehicle> vehicleEntities = vehiclesRepository.allVehicles();
        List<VehicleDTO> vehicleDTOS = vehicleEntities
                .stream()
                .map(vehicle -> new VehicleDTO(
                                vehicle.getBrand(),
                                vehicle.getModel(),
                                vehicle.getManufacturingDate(),
                                vehicle.getNumberOfKilometers(),
                                vehicle.getDoors(),
                                vehicle.getPrice(),
                                vehicle.getCurrency(),
                                new ArrayList<>(
                                        vehicle.getServiceVehicles()
                                                .stream()
                                                .map(
                                                        service -> new ServiceVehicleDTO(
                                                                service.getDate(),
                                                                service.getKilometers(),
                                                                service.getDescriptions())
                                                )
                                                .toList()),
                                vehicle.getCountOfOwners()
                        )
                ).toList();

        return new ResponseVehiclesDTO(
                "Vehículos fabricados entre " + since + " " + to,
                vehicleDTOS
                        .stream()
                        .filter(
                                vehicle -> vehicle.getManufacturingDate().isAfter(since) || vehicle.getManufacturingDate().isEqual(since) && vehicle.getManufacturingDate().isBefore(to) || vehicle.getManufacturingDate().isEqual(to))
                        .toList());
    }

    // 4 - Retorna un vehiculo por ID
    public ResponseVehicleDTO getVehicleById(String id) {
        Optional<Vehicle> vehicleOptional = vehiclesRepository.findVehicleById(id);

        if (vehicleOptional.isPresent()) {
            Vehicle vehicleEntity = vehicleOptional.get();

            new ResponseVehicleDTO(
                    "Se ha encontrado el vehiculo solicitado con el ID " + id,
                    new VehicleDTO(
                        vehicleEntity.getBrand(),
                        vehicleEntity.getModel(),
                        vehicleEntity.getManufacturingDate(),
                        vehicleEntity.getNumberOfKilometers(),
                        vehicleEntity.getDoors(),
                        vehicleEntity.getPrice(),
                        vehicleEntity.getCurrency(),
                        new ArrayList<>(
                                vehicleEntity.getServiceVehicles()
                                        .stream()
                                        .map(
                                                service -> new ServiceVehicleDTO(
                                                        service.getDate(),
                                                        service.getKilometers(),
                                                        service.getDescriptions())
                                        )
                                        .toList()),
                        vehicleEntity.getCountOfOwners()
                    ));
        }

        throw new VehicleNotFoundException("Vehiculo no encontrado");
    }

}
