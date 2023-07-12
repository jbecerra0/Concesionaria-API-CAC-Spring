package com.jbecerra.Concesionaria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jbecerra.Concesionaria.dto.request.RequestVehicleDto;
import com.jbecerra.Concesionaria.dto.response.ResponseVehicleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void addNewVehicleTest() throws Exception {
        RequestVehicleDto requestVehicleDto = new RequestVehicleDto("Toyota", "Corolla", LocalDate.of(2023, 9, 10), 0L, "5", "1500000", "AR", List.of(), "0");
        ResponseVehicleDto responseVehicleDto = new ResponseVehicleDto("Vehiculo agregado con exito!", requestVehicleDto);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .writer();

        String request = writer.writeValueAsString(requestVehicleDto);
        String expectedResponse = writer.writeValueAsString(responseVehicleDto);

        MvcResult mvcResult = mockMvc.perform(post("/v1/api/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }
}
