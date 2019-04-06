package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    Booking getBooking(Long id);

    List<Booking> getAll();

    Booking update(Booking booking);

    void delete(Booking booking);
}
