package lsit.Models;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    public UUID id;
    public UUID carId;
    public UUID customerId;
    public LocalDate startDate;
    public LocalDate endDate;

    public Booking(UUID carId, UUID customerId, String startDate, String endDate) {
        this.id = UUID.randomUUID();
        this.carId = carId;
        this.customerId = customerId;
        setStartDate(startDate);
        setEndDate(endDate);
    }
    public void setStartDate(String startDate) {
        try {
            this.startDate = LocalDate.parse(startDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid start date format. Use YYYY-MM-DD.");
        }
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid end date format. Use YYYY-MM-DD.");
        }
    }
}
