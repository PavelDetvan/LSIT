package lsit.Repositories;

import java.util.*;

import org.springframework.stereotype.Repository;

import lsit.Models.Car;

@Repository
public class CarRepository {
    static HashMap<UUID, Car> cars = new HashMap<>();

    public void add(Car car){
        car.id = UUID.randomUUID();
        cars.put(car.id, car);
    }

    public Car get(UUID id){
        return cars.get(id);
    }

    public void remove(UUID id){
        cars.remove(id);
    }

    public void update(Car car){
        Car existingCar = cars.get(car.id);
        existingCar.brand = car.brand;
        existingCar.model = car.model;
        existingCar.availabilityStatus = car.availabilityStatus;
        existingCar.rentalPrice = car.rentalPrice;
    }

    public List<Car> list(){
        return new ArrayList<>(cars.values());
    }
}