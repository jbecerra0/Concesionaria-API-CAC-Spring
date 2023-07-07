package com.jbecerra.Concesionaria.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceVehicleDTO {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String kilometers;
    private String descriptions;
}
