package com.jbecerra.Concesionaria.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate manufacturingDate;
    private Long numberOfKilometers;
    private String doors;
    private String price;
    private String currency;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<ServiceVehicle> servicesVehicle;
    private String countOfOwners;
}
