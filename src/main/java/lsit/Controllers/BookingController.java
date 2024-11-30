package lsit.Controllers;

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
    public List<Booking> list() {
        return bookingRepository.list();
    }

    @GetMapping("/{id}")
    public Booking get(@PathVariable("id") UUID id) {
        return bookingRepository.get(id);
    }

    @PostMapping
    public Booking add(@RequestBody Booking booking) {
        bookingRepository.add(booking);
        return booking;
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable("id") UUID id, @RequestBody Booking booking) {
        booking.id = id;
        bookingRepository.update(booking);
        return booking;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        bookingRepository.remove(id);
    }
}
