package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.ServiceVehicleDTO;
import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.entity.ServiceVehicle;
import com.jbecerra.Concesionaria.entity.Vehicle;
import com.jbecerra.Concesionaria.exceptions.InvalidDateRangeException;
import com.jbecerra.Concesionaria.exceptions.VehicleNotFoundException;
import com.jbecerra.Concesionaria.exceptions.VehiclesNotFoundException;
import com.jbecerra.Concesionaria.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
            throw new InvalidDateRangeException("La fecha de inicio (" + since + ") no puede ser posterior a la fecha de fin (" + since + ").");
        }

        List<Vehicle> vehicles = vehicleRepository.findByManufacturingDateBetween(since, to);

        if (vehicles.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", Locale.of("es", "ES"));
            throw new VehiclesNotFoundException("No se encontraron vehiculos manufacturados entre " + since.format(formatter) + " y el " + to.format(formatter));
        }

        ModelMapper mapper = new ModelMapper();

        List<VehicleDTO> vehicleDTOS = vehicles.stream().map(vehicle -> {
            List<ServiceVehicleDTO> serviceVehicleDTOS = vehicle
                    .getServiceVehicles()
                    .stream()
                    .map(serviceVehicle -> mapper.map(serviceVehicle, ServiceVehicleDTO.class))
                    .toList();
            VehicleDTO vehicleDTO = mapper.map(vehicle, VehicleDTO.class);
            vehicleDTO.setServices(serviceVehicleDTOS);
            return vehicleDTO;
        }).toList();

        return new ResponseVehiclesDTO("Se han encontrado vehiculos manufacturados entre las 2 fechas proporcionadas", vehicleDTOS);
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
