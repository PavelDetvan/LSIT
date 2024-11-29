package lsit.Models;

import java.util.UUID;

public class Car {
    public UUID id;
    public String brand;
    public String model;
    public boolean availabilityStatus;
    public double rentalPrice;

    public Car(String brand, String model, double rentalPrice) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.availabilityStatus = true;  // Default to available
        this.rentalPrice = rentalPrice;
    }
}
