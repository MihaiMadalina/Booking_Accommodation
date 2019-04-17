package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.*;
import ro.sda.booking.core.service.BookingService;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.HostService;
import ro.sda.booking.core.service.PropertyService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @Autowired
    private HostService hostService;

    @Test
    @Rollback(false)
    @Transactional
    public void createBookingTest(){
        Client client = new Client();
        Property property = new Property();
        Host host = new Host();

        client.setName("Alina Cristiana");
        client.setEmail("alina_cristiana@gmail.com");
        client.setContact("01255999");
        clientService.create(client);

        host.setName("Boz");
        host.setEmail("andreea.boz@gmail.com");
        hostService.create(host);

        property.setName("Bucium");
        property.setTelephone("25555888");
        property.setEmail("contact@bucium.com");
        property.setAddress("Iasi");
        property.setHost(host);
        propertyService.create(property);

        Booking expectedBooking = new Booking();
        Date checkInDate = new GregorianCalendar(2019, Calendar.APRIL, 1).getTime();
        expectedBooking.setCheckIn(checkInDate);
        Date checkOutDate = new GregorianCalendar(2019, Calendar.MAY, 7).getTime();
        expectedBooking.setCheckOut(checkOutDate);

        expectedBooking.setPersonsNo(1);
        expectedBooking.setRoomType(RoomType.SINGLE);
        expectedBooking.setRoomsNo(1);
        expectedBooking.setBookingDate();
        expectedBooking.setClient(client);
        expectedBooking.setProperty(property);
        expectedBooking.onCreate();
        bookingService.create(expectedBooking);
        List<Booking> bookings = bookingService.getAll();
        Assert.assertEquals(expectedBooking, bookings.get(0));
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
        Booking booking = bookingService.getBooking(1L);

        Client client = new Client();
        Property property = new Property();
        Host host = new Host();

        client.setName("Mihai Madalina");
        client.setEmail("madalina.mihai@gmail.com");
        client.setContact("015577889");
        clientService.create(client);

        host.setName("Boz");
        host.setEmail("sorina.boz@gmail.com");
        hostService.create(host);

        property.setName("Traian");
        property.setTelephone("45599965");
        property.setEmail("contact@traian.com");
        property.setAddress("Iasi");
        property.setHost(host);
        propertyService.create(property);

        Date checkInDate = new GregorianCalendar(2020, Calendar.JANUARY, 28).getTime();
        booking.setCheckIn(checkInDate);
        Date checkOutDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 7).getTime();
        booking.setCheckOut(checkOutDate);

        booking.setPersonsNo(2);
        booking.setRoomType(RoomType.DOUBLE);
        booking.setRoomsNo(1);
        booking.setBookingDate();
        booking.setClient(client);
        booking.setProperty(property);
        booking.onCreate();

        Booking expectedBooking = bookingService.update(booking);
        Booking actualBooking = bookingService.getBooking(1L);
        Assert.assertEquals(expectedBooking, actualBooking);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteBookingTest() {
        List<Booking> bookings = bookingService.getAll();
        int size = bookings.size();
        Booking booking = bookingService.getBooking(1L);
        bookingService.delete(booking);
        bookings = bookingService.getAll();
        Assert.assertEquals(size - 1, bookings.size());
    }
}