package lsit.Models;

import java.util.UUID;

public class Car {
    public UUID id;
    public String brand;
    public String model;
    public boolean availabilityStatus;
    public double rentalPrice;

    public Car(String brand, String model, double rentalPrice) {
        validateBrandAndModel(brand, model);
        validateRentalPrice(rentalPrice);
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.availabilityStatus = true;  // Default to available
        this.rentalPrice = rentalPrice;
    }
    
    private void validateBrandAndModel(String brand, String model) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
    }

    private void validateRentalPrice(double rentalPrice) {
        if (rentalPrice < 0) {
            throw new IllegalArgumentException("Rental price cannot be negative.");
        }
    }
}
