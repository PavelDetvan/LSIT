package lsit.Models;

import java.util.UUID;

public class Booking {
    public UUID id;
    public UUID carId;
    public UUID customerId;
    public String startDate;
    public String endDate;

    public Booking(UUID carId, UUID customerId, String startDate, String endDate) {
        this.id = UUID.randomUUID();
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
