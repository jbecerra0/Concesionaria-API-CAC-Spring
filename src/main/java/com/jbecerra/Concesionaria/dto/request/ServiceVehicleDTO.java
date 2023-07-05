package com.jbecerra.Concesionaria.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceVehicleDTO {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String kilometers;
    private String descriptions;
}
