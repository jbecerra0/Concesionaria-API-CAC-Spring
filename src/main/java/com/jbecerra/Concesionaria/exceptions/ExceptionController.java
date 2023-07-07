package com.jbecerra.Concesionaria.exceptions;

import com.jbecerra.Concesionaria.dto.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<?> vehicleNotFound(VehicleNotFoundException e) {
        return new ResponseEntity<>(new ErrorDTO("No existe el vehiculo solicitado", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<?> invalidDateRange(InvalidDateRangeException e) {
        return new ResponseEntity<>(new ErrorDTO("Fechas ingresadas son invalidas", e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
