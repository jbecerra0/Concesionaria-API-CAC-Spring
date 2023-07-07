package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.entity.ServiceVehicle;
import com.jbecerra.Concesionaria.entity.Vehicle;
import com.jbecerra.Concesionaria.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

        return new ResponseVehicleDTO();
    }

    @Override
    public ResponseVehiclesDTO getAllUsedVehicles() {
        return null;
    }

    @Override
    public ResponseVehiclesDTO vehiclesSinceTo(LocalDate since, LocalDate to) {
        return null;
    }

    @Override
    public ResponseVehicleDTO getVehicleById(Long id) {
        return null;
    }
}
