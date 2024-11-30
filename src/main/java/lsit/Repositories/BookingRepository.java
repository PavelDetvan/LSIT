package lsit.Repositories;

import java.util.*;

import org.springframework.stereotype.Repository;

import lsit.Models.Booking;

@Repository
public class BookingRepository {
    static HashMap<UUID, Booking> bookings = new HashMap<>();

    public void add(Booking booking) {
        bookings.put(booking.id, booking);
    }

    public Booking get(UUID id) {
        return bookings.get(id);
    }

    public void remove(UUID id) {
        bookings.remove(id);
    }

    public void update(Booking booking) {
        Booking existingBooking = bookings.get(booking.id);
        if (existingBooking != null) {
            existingBooking.carId = booking.carId;
            existingBooking.customerId = booking.customerId;
            existingBooking.startDate = booking.startDate;
            existingBooking.endDate = booking.endDate;
        }
    }

    public List<Booking> list() {
        return new ArrayList<>(bookings.values());
    }
}
