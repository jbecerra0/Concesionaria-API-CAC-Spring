package com.jbecerra.Concesionaria.exceptions;

public class VehiclesNotFoundException extends RuntimeException {
    public VehiclesNotFoundException(String message) {
        super(message);
    }
}
