package com.alex.TDDTestApp.cache;


import com.alex.TDDTestApp.model.Car;
import com.alex.TDDTestApp.repository.CarRepository;
import com.alex.TDDTestApp.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CacheTest {

    @MockBean
    CarRepository carRepository;

    @Autowired
    CarService carService;


    @Test
    void cacheTest() {
        given(carRepository.findByName("alex")).willReturn(Optional.of(new Car("alex", "hatchback")));

        Car car = carService.getCarDetails("alex");
        assertNotNull(car);
        carService.getCarDetails("alex");

        Mockito.verify(carRepository, Mockito.times(1)).findByName("alex");

    }


}
