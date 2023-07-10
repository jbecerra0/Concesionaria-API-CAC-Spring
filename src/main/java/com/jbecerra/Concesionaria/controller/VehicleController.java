package com.jbecerra.Concesionaria.controller;

import com.jbecerra.Concesionaria.dto.request.RequestVehicleDto;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDto;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDto;
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
    public ResponseEntity<ResponseVehicleDto> addNewVehicle(@RequestBody RequestVehicleDto newVehicleDto) {
        return new ResponseEntity<>(vehicleService.addVehicle(newVehicleDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseVehiclesDto> getAllUsedVehicles() {
        return new ResponseEntity<>(vehicleService.getAllUsedVehicles(), HttpStatus.OK);
    }

    @GetMapping("dates")
    public ResponseEntity<ResponseVehiclesDto> getAllManufacturedSinceTo(
            @RequestParam
            @DateTimeFormat(pattern = "yyyy.MM.dd")
            LocalDate since,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy.MM.dd")
            LocalDate to) {
        return new ResponseEntity<>(vehicleService.vehiclesSinceTo(since, to), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseVehicleDto> getVehicleById(@PathVariable Long id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }
}
