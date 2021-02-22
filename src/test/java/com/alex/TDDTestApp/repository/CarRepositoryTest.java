package com.alex.TDDTestApp.repository;

import com.alex.TDDTestApp.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindByName() {
        Optional<Car> car = carRepository.findByName("duster");
        assertTrue(car.isPresent());
    }

    @Test
    public void testFindByName_Not_Found(){
        Optional<Car> car = carRepository.findByName("alex");
        assertFalse(car.isPresent());
    }
}
