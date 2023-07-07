package com.jbecerra.Concesionaria.controller;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.service.VehicleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("v1/api/vehicles")
public class VehicleController {
    final private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ResponseVehicleDTO> addNewVehicle(@RequestBody VehicleDTO newVehicleDTO) {
        return new ResponseEntity<>(vehicleService.addVehicle(newVehicleDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseVehiclesDTO> getAllUsedVehicles() {
        return new ResponseEntity<>(vehicleService.getAllUsedVehicles(), HttpStatus.OK);
    }

    @GetMapping("dates")
    public ResponseEntity<ResponseVehiclesDTO> getAllManufacturedSinceTo(
            @RequestParam
            @DateTimeFormat(pattern = "yyyy.MM.dd")
            LocalDate since,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy.MM.dd")
            LocalDate to) {
        return new ResponseEntity<>(vehicleService.vehiclesSinceTo(since, to), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseVehicleDTO> getVehicleById(@PathVariable Long id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }
}
