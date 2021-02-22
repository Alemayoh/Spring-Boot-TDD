package com.alex.TDDTestApp.service;

import com.alex.TDDTestApp.exception.CarNotFoundException;
import com.alex.TDDTestApp.model.Car;
import com.alex.TDDTestApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Cacheable("car")
    public Car getCarDetails(String name) {
        Car car =null;
        Optional<Car> carOptional = carRepository.findByName(name);
        if(carOptional.isPresent()){
            car = carOptional.get();
        }else{
            throw new CarNotFoundException();
        }
        return car;
    }
}
