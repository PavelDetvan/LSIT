package lsit.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import lsit.Models.Booking;
import lsit.Repositories.BookingRepository;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")  // Admins and Employees can view all bookings
    public List<Booking> list() {
        return bookingRepository.list();
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.attributes['sub'] or hasRole('ADMIN') or hasRole('EMPLOYEE')")  // Customers can only view their own booking, Employees and Admins can view any booking
    public Booking get(@PathVariable("id") UUID id) {
        return bookingRepository.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('CUSTOMER')")  // All roles can create booking
    public Booking add(@RequestBody Booking booking) {
        validateBooking(booking);
        bookingRepository.add(booking);
        return booking;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")  // Employees and Admins can update bookings
    public Booking update(@PathVariable("id") UUID id, @RequestBody Booking booking) {
        validateBooking(booking);
        booking.id = id;
        bookingRepository.update(booking);
        return booking;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Only admins can delete bookings
    public void delete(@PathVariable("id") UUID id) {
        bookingRepository.remove(id);
    }
    
    private void validateBooking(Booking booking) {
        if (booking.startDate == null || booking.endDate == null) {
            throw new IllegalArgumentException("Start date and end date must be provided.");
        }

        if (booking.startDate.isAfter(booking.endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        if (booking.carId == null || booking.customerId == null) {
            throw new IllegalArgumentException("Car ID and Customer ID must be provided.");
        }
    }
}
