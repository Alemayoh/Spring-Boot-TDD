package com.alex.TDDTestApp;

import com.alex.TDDTestApp.model.Car;
import com.alex.TDDTestApp.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CarService carService;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getCarDetails() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Car> response = restTemplate.exchange(
                "http://localhost:" + port + "/cars/duster", HttpMethod.GET, entity, Car.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("hybrid", response.getBody().getType());
    }
    @Test
    public void getCarDetails_NotFound() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Car> response = restTemplate.exchange(
                "http://localhost:" + port + "/cars/alex", HttpMethod.GET, entity, Car.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
