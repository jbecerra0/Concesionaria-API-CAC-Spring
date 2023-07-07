package com.jbecerra.Concesionaria.repository;

import com.jbecerra.Concesionaria.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByManufacturingDateBetween(LocalDate since, LocalDate to);
}
