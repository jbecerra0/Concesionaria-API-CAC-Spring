package com.jbecerra.Concesionaria.controller;

import com.jbecerra.Concesionaria.dto.request.VehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDTO;
import com.jbecerra.Concesionaria.dto.response.ResponseVehiclesDTO;
import com.jbecerra.Concesionaria.service.VehiclesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("v1/api/vehicles")
public class VehiclesController {
    final private VehiclesService vehiclesService;

    public VehiclesController(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @PostMapping
    public ResponseEntity<ResponseVehicleDTO> addNewVehicle(@RequestBody VehicleDTO newVehicleDTO) {
        return new ResponseEntity<>(vehiclesService.addVehicle(newVehicleDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseVehiclesDTO> getAllUsedVehicles() {
        return new ResponseEntity<>(vehiclesService.getAllUsedVehicles(), HttpStatus.OK);
    }

    @GetMapping("dates")
    public ResponseEntity<ResponseVehiclesDTO> getAllManufacturedSinceTo(
            @RequestParam
            @DateTimeFormat(pattern = "yyyy.MM.dd")
            LocalDate since,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy.MM.dd")
            LocalDate to) {
        return new ResponseEntity<>(vehiclesService.vehiclesSinceTo(since, to), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseVehicleDTO> getVehicleById(@PathVariable String id) {
        return new ResponseEntity<>(vehiclesService.getVehicleById(id), HttpStatus.OK);
    }
}
