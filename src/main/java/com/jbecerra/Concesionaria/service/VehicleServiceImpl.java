package com.jbecerra.Concesionaria.service;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.entity.Vehicle;
import com.jbecerra.Concesionaria.exceptions.InvalidDateRangeException;
import com.jbecerra.Concesionaria.exceptions.VehicleNotFoundException;
import com.jbecerra.Concesionaria.exceptions.VehiclesNotFoundException;
import com.jbecerra.Concesionaria.repository.VehicleRepository;
import com.jbecerra.Concesionaria.mapper.VehicleMapper;
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
        Vehicle newVehicle = VehicleMapper.convertDTOToEntity(vehicleDTO);

        vehicleRepository.save(newVehicle);

        return new ResponseVehicleDTO("Vehiculo agregado con exito!", vehicleDTO);
    }

    @Override
    public ResponseVehiclesDTO getAllUsedVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            throw new VehiclesNotFoundException("No se han encontraron vehiculos.");
        }

        vehicles = vehicles.stream()
                .filter(vehicle -> Integer.parseInt(vehicle.getCountOfOwners()) >= 1)
                .toList();

        if (vehicles.isEmpty()) {
            throw new VehiclesNotFoundException("No se han encontraron vehiculos usados.");
        }

        List<VehicleDTO> vehiclesDTO = VehicleMapper.convertEntitiesToDTOS(vehicles);

        return new ResponseVehiclesDTO("Vehiculos usados solicitados", vehiclesDTO);
    }

    @Override
    public ResponseVehiclesDTO vehiclesSinceTo(LocalDate since, LocalDate to) {
        if (since.isAfter(to)) {
            throw new InvalidDateRangeException("La fecha de inicio (" + since + ") no puede ser posterior a la fecha de fin (" + since + ").");
        }

        List<Vehicle> vehicles = vehicleRepository.findByManufacturingDateBetween(since, to);

        if (vehicles.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            throw new VehiclesNotFoundException("No se encontraron vehiculos manufacturados entre " + since.format(formatter) + " y el " + to.format(formatter));
        }

        List<VehicleDTO> vehiclesDTO = VehicleMapper.convertEntitiesToDTOS(vehicles);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

        return new ResponseVehiclesDTO("Vehiculos manufacturados entre el " + since.format(formatter) + " y el " + to.format(formatter) + ".", vehiclesDTO);
    }

    @Override
    public ResponseVehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            throw new VehicleNotFoundException("No se ha encontrado ningun vehiculo con el ID " + id + ".");
        }

        VehicleDTO vehicleDTO = VehicleMapper.convertEntityToDTO(vehicle.get());

        return new ResponseVehicleDTO("Vehiculo solicitado con el ID " + id + ".", vehicleDTO);
    }
}
