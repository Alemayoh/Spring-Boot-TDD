package com.alex.TDDTestApp.controller;

import com.alex.TDDTestApp.exception.CarNotFoundException;
import com.alex.TDDTestApp.model.Car;
import com.alex.TDDTestApp.service.CarService;
import jdk.net.SocketFlow;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;


    @Test
    public void getCarDetailsTest() throws Exception {
        given(carService.getCarDetails(Mockito.anyString())).willReturn(new Car("alex", "solomon"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/alex"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("name").value("alex"))
                .andExpect(jsonPath("type").value("solomon"));
    }
    @Test
    public void carGet_NotFound_HttpStatus() throws Exception {
        given(carService.getCarDetails(Mockito.anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/alex"))
                .andExpect(status().isNotFound());
    }
}
