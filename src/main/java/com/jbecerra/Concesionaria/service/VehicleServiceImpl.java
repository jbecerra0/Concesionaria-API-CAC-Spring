package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.ServiceVehicleDTO;
import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.entity.ServiceVehicle;
import com.jbecerra.Concesionaria.entity.Vehicle;
import com.jbecerra.Concesionaria.exceptions.InvalidDateRangeException;
import com.jbecerra.Concesionaria.exceptions.VehicleNotFoundException;
import com.jbecerra.Concesionaria.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    final private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ResponseVehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        ModelMapper mapper = new ModelMapper();

        List<ServiceVehicle> serviceVehicles = vehicleDTO.getServices()
                .stream()
                .map(service -> mapper.map(service, ServiceVehicle.class))
                .toList();

        Vehicle newVehicle = mapper.map(vehicleDTO, Vehicle.class);
        newVehicle.setServiceVehicles(serviceVehicles);

        serviceVehicles.forEach(serviceVehicle -> serviceVehicle.setVehicle(newVehicle));

        vehicleRepository.save(newVehicle);

        return new ResponseVehicleDTO("Vehiculo agregado con exito!", vehicleDTO);
    }

    @Override
    public ResponseVehiclesDTO getAllUsedVehicles() {
        return null;
    }

    @Override
    public ResponseVehiclesDTO vehiclesSinceTo(LocalDate since, LocalDate to) {
        if (since.isAfter(to)) {
            throw new InvalidDateRangeException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        return null;
    }

    @Override
    public ResponseVehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            throw new VehicleNotFoundException("Vehiculo solicitado no encontrado");
        }

        ModelMapper mapper = new ModelMapper();

        List<ServiceVehicleDTO> serviceVehicleDTOS = vehicle.get()
                .getServiceVehicles()
                .stream()
                .map(serviceVehicle -> mapper.map(serviceVehicle, ServiceVehicleDTO.class))
                .toList();

        VehicleDTO vehicleDTO = mapper.map(vehicle.get(), VehicleDTO.class);
        vehicleDTO.setServices(serviceVehicleDTOS);

        return new ResponseVehicleDTO("Vehiculo solicitado encontrado", vehicleDTO);
    }
}
