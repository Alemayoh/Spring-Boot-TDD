package com.alex.TDDTestApp.service;

import com.alex.TDDTestApp.exception.CarNotFoundException;
import com.alex.TDDTestApp.model.Car;
import com.alex.TDDTestApp.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getCarDetailsTest() throws Exception{
        given(carRepository.findByName("alex")).willReturn(Optional.of( new Car("alex", "solomon")));

            Car car = carService.getCarDetails("alex");
            assertNotNull(car);
            assertEquals("alex", car.getName());
            assertEquals("solomon", car.getType());
    }

    @Test
    public void getCar_NotFound_Test(){
        given(carRepository.findByName("alex")).willThrow(new CarNotFoundException());

        assertThrows(CarNotFoundException.class, ()->carService.getCarDetails("alex"));
    }
}
