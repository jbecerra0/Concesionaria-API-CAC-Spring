package com.jbecerra.Concesionaria.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "service_vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String kilometers;
    private String descriptions;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}
