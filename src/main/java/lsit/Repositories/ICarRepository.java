package lsit.Repositories;

import java.util.*;
import lsit.Models.Car;

public interface ICarRepository {
    void add(Car car);
    Car get(UUID id);
    void remove(UUID id);
    void update(Car car);
    List<Car> list();
}