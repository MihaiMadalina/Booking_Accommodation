package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.service.BookingService;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.PropertyService;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class BookingServiceImplementationTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    @Transactional
    public void createBookingTest(){
        Booking booking = new Booking();
        Property property = new Property();
        Client client = new Client();
        booking.setProperty(propertyService.getProperty(1L));
        booking.setClient(clientService.getClientById(2L));
        LocalDate checkInDate = LocalDate.of(2019, 4, 7);
        booking.setCheckIn(checkInDate);
        LocalDate checkOutDate = LocalDate.of(2019, 4, 10);
        booking.setCheckOut(checkOutDate);
        booking.setPersonsNo(2);
        booking.setRoomType("double");
        booking.setRoomsNo(1);
        LocalDate bookingDate = LocalDate.of(2019, 4, 6);
        booking.setBookingDate(bookingDate);
        bookingService.create(booking);
        Assert.assertNotNull(booking);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getBookingByIdTest() {
        Booking expectedBooking = bookingService.getBooking(1L);
        List<Booking> bookings = bookingService.getAll();
        Assert.assertEquals(expectedBooking, bookings.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllBookingsTest() {
        List<Booking> bookings = bookingService.getAll();
        Assert.assertEquals(1, bookings.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateBookingTest() {
        Booking booking = bookingService.getBooking(2L);
        booking.setProperty(propertyService.getProperty(1L));
        booking.setClient(clientService.getClientById(2L));
        LocalDate checkInDate = LocalDate.of(2019, 8, 21);
        booking.setCheckIn(checkInDate);
        LocalDate checkOutDate = LocalDate.of(2019, 8, 28);
        booking.setCheckOut(checkOutDate);
        booking.setPersonsNo(1);
        booking.setRoomType("single");
        booking.setRoomsNo(1);
        LocalDate bookingDate = LocalDate.of(2019, 4, 6);
        booking.setBookingDate(bookingDate);
        Booking expectedBooking = bookingService.update(booking);
        Booking actualBooking = bookingService.getBooking(2L);
        Assert.assertEquals(expectedBooking, actualBooking);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteBookingTest() {
        List<Booking> bookings = bookingService.getAll();
        int size = bookings.size();
        Booking booking = bookingService.getBooking(3L);
        bookingService.delete(booking);
        bookings = bookingService.getAll();
        Assert.assertEquals(size - 1, bookings.size());
    }
}