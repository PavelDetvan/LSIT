package lsit.Controllers;

import java.util.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lsit.Models.Car;
import lsit.Repositories.CarRepository;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('CUSTOMER')")  // All roles can view cars
    public List<Car> list(){
        return carRepository.list();
    }

    @GetMapping("/cars/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('CUSTOMER')")  // All roles can view cars
    public Car get(@PathVariable("id") UUID id){
        return carRepository.get(id);
    }

    @PostMapping("/cars")
    @PreAuthorize("hasRole('ADMIN')")  // Only admins can add cars
    public Car add(@RequestBody Car car){
        validateCar(car);
        carRepository.add(car);
        return car;
    }

    @PutMapping("/cars/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")  // Employees or Admins can update cars
    public Car update(@PathVariable("id") UUID id, @RequestBody Car car){
        validateCar(car);
        car.id = id;
        carRepository.update(car);
        return car;
    }

    @DeleteMapping("/cars/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Only admins can delete cars
    public void delete(@PathVariable("id") UUID id){
        carRepository.remove(id);
    }

    private void validateCar(Car car) {
        if (car.brand == null || car.brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        if (car.model == null || car.model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        if (car.rentalPrice < 0) {
            throw new IllegalArgumentException("Rental price cannot be negative.");
        }
    }
}
