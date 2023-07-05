package com.jbecerra.Concesionaria.repository;

import com.jbecerra.Concesionaria.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehiclesRepository {
    ArrayList<Vehicle> listVehicles = new ArrayList<>();

    public boolean addVehicle(Vehicle vehicle) {
        int lastSize = listVehicles.size();
        listVehicles.add(vehicle);
        // System.out.println(listVehicles);
        return listVehicles.size() > lastSize;
    }

    public List<Vehicle> allVehicles() {
        return listVehicles;
    }

    public Optional<Vehicle> findVehicleById(String id) {
        return listVehicles
                .stream()
                .filter(vehicle -> vehicle.getId().equalsIgnoreCase(id))
                .findFirst();
    }

}
