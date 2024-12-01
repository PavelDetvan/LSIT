package lsit.Controllers;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lsit.Models.Car;
import lsit.Repositories.CarRepository;

@RestController
public class CarController {

    CarRepository carRepository;

    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> list(){
        return carRepository.list();
    }

    @GetMapping("/cars/{id}")
    public Car get(@PathVariable("id") UUID id){
        return carRepository.get(id);
    }

    @PostMapping("/cars")
    public Car add(@RequestBody Car car){
        carRepository.add(car);
        return car;
    }

    @PutMapping("/cars/{id}")
    public Car update(@PathVariable("id") UUID id, @RequestBody Car car){
        car.id = id;
        carRepository.update(car);
        return car;
    }

    @DeleteMapping("/cars/{id}")
    public void delete(@PathVariable("id") UUID id){
        carRepository.remove(id);
    }
}
