package com.jbecerra.Concesionaria.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceVehicle {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String kilometers;
    private String descriptions;
}
