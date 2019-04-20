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
import ro.sda.booking.core.service.*;

import java.util.*;

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

    @Autowired
    AvailabilityService availabilityService;

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
        Date checkInDate = new GregorianCalendar(2019, Calendar.APRIL, 5).getTime();
        expectedBooking.setCheckIn(checkInDate);
        Date checkOutDate = new GregorianCalendar(2019, Calendar.APRIL, 25).getTime();
        expectedBooking.setCheckOut(checkOutDate);

        Availability availability1 = new Availability();
        availability1.setProperty(property);
        availability1.setRoomName("Room1");
        availability1.setRoomType(RoomType.SINGLE);
        availability1.setPriceSingle(100.00);
        Date availableFromDate = new GregorianCalendar(2019, Calendar.APRIL, 1).getTime();
        availability1.setFromDate(availableFromDate);
        Date availableToDate = new GregorianCalendar(2019, Calendar.APRIL, 30).getTime();
        availability1.setToDate(availableToDate);
        availabilityService.create(availability1);

        Availability availability2 = new Availability();
        availability2.setProperty(property);
        availability2.setRoomName("Room2");
        availability2.setRoomType(RoomType.SINGLE);
        availability2.setPriceSingle(100.00);
        Date availableFromDate2 = new GregorianCalendar(2019, Calendar.APRIL, 5).getTime();
        availability2.setFromDate(availableFromDate2);
        Date availableToDate2 = new GregorianCalendar(2019, Calendar.APRIL, 30).getTime();
        availability2.setToDate(availableToDate2);
        availabilityService.create(availability2);

        Availability availability3 = new Availability();
        availability3.setProperty(property);
        availability3.setRoomName("Room3");
        availability3.setRoomType(RoomType.SINGLE);
        availability3.setPriceSingle(100.00);
        Date availableFromDate3 = new GregorianCalendar(2019, Calendar.APRIL, 1).getTime();
        availability3.setFromDate(availableFromDate3);
        Date availableToDate3 = new GregorianCalendar(2019, Calendar.APRIL, 25).getTime();
        availability3.setToDate(availableToDate3);
        availabilityService.create(availability3);

        Availability availability4 = new Availability();
        availability4.setProperty(property);
        availability4.setRoomName("Room4");
        availability4.setRoomType(RoomType.SINGLE);
        availability4.setPriceSingle(100.00);
        Date availableFromDate4 = new GregorianCalendar(2019, Calendar.APRIL, 5).getTime();
        availability4.setFromDate(availableFromDate4);
        Date availableToDate4 = new GregorianCalendar(2019, Calendar.APRIL, 25).getTime();
        availability4.setToDate(availableToDate4);
        availabilityService.create(availability4);



        expectedBooking.setPersonsNo(4);
        expectedBooking.setRoomType(RoomType.SINGLE);
        expectedBooking.setRoomsNo(4);
        expectedBooking.setBookingDate();
        expectedBooking.setClient(client);
        expectedBooking.setProperty(property);
        expectedBooking.onCreate();
        List<Availability> availabilities = new ArrayList<>();
        availabilities.add(availability1);
        availabilities.add(availability2);
        availabilities.add(availability3);
        availabilities.add(availability4);
        expectedBooking.setAvailabilityList(availabilities);
        bookingService.create(expectedBooking);
        List<Booking> bookings = bookingService.getAll();
        Assert.assertEquals(expectedBooking, bookings.get(0));
    }

//    @Test
//    @Transactional
//    @Rollback(false)
//    public void getBookingByIdTest() {
//        Booking expectedBooking = bookingService.getBooking(1L);
//        List<Booking> bookings = bookingService.getAll();
//        Assert.assertEquals(expectedBooking, bookings.get(0));
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void getAllBookingsTest() {
//        List<Booking> bookings = bookingService.getAll();
//        Assert.assertEquals(1, bookings.size());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void updateBookingTest() {
//        Booking booking = bookingService.getBooking(1L);
//
//        Client client = new Client();
//        Property property = new Property();
//        Host host = new Host();
//
//        client.setName("Mihai Madalina");
//        client.setEmail("madalina.mihai@gmail.com");
//        client.setContact("015577889");
//        clientService.create(client);
//
//        host.setName("Boz");
//        host.setEmail("sorina.boz@gmail.com");
//        hostService.create(host);
//
//        property.setName("Traian");
//        property.setTelephone("45599965");
//        property.setEmail("contact@traian.com");
//        property.setAddress("Iasi");
//        property.setHost(host);
//        propertyService.create(property);
//
//        Date checkInDate = new GregorianCalendar(2020, Calendar.JANUARY, 28).getTime();
//        booking.setCheckIn(checkInDate);
//        Date checkOutDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 7).getTime();
//        booking.setCheckOut(checkOutDate);
//
//        booking.setPersonsNo(2);
//        booking.setRoomType(RoomType.DOUBLE);
//        booking.setRoomsNo(1);
//        booking.setBookingDate();
//        booking.setClient(client);
//        booking.setProperty(property);
//        booking.onCreate();
//
//        Booking expectedBooking = bookingService.update(booking);
//        Booking actualBooking = bookingService.getBooking(1L);
//        Assert.assertEquals(expectedBooking, actualBooking);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void deleteBookingTest() {
//        List<Booking> bookings = bookingService.getAll();
//        int size = bookings.size();
//        Booking booking = bookingService.getBooking(1L);
//        bookingService.delete(booking);
//        bookings = bookingService.getAll();
//        Assert.assertEquals(size - 1, bookings.size());
//    }
}