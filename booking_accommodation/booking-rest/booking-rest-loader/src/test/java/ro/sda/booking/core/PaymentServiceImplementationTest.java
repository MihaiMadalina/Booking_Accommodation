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

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PaymentServiceImplementationTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private HostService hostService;

    @Autowired
    private PropertyService propertyService;

    @Test
    @Rollback(false)
    @Transactional
    public void createPaymentTest(){

        Client client = new Client();
        client.setName("Marinela Istrate");
        client.setEmail("istrate.marinela93@yahoo.com");
        client.setContact("0248877555");
        clientService.create(client);

        Host host = new Host();
        host.setName("Boz");
        host.setEmail("andreea.boz@gmail.com");
        hostService.create(host);

        Property property = new Property();
        property.setName("Ramada");
        property.setTelephone("04587566");
        property.setEmail("contact@ramada.com");
        property.setAddress("Brasov");
        property.setHost(host);
        propertyService.create(property);

        Booking booking = new Booking();
        Date checkInDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 6).getTime();
        booking.setCheckIn(checkInDate);
        Date checkOutDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 21).getTime();
        booking.setCheckOut(checkOutDate);

        booking.setPersonsNo(4);
        booking.setRoomType(RoomType.DOUBLE);
        booking.setRoomsNo(2);
        booking.setBookingDate();
        booking.setClient(client);
        booking.setProperty(property);
        booking.onCreate();
        bookingService.create(booking);

        Payment expectedPayment = new Payment();
        expectedPayment.setAmount(120.0);
        expectedPayment.setBooking(booking);
        expectedPayment.setPaymentDate();
        paymentService.create(expectedPayment);
        List<Payment> payments = paymentService.getAll();
        Assert.assertEquals(expectedPayment, payments.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getPaymentByIdTest() {
        Payment expectedPayment = paymentService.getPayment(1L);
        List<Payment> payments = paymentService.getAll();
        Assert.assertEquals(expectedPayment, payments.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllPaymentsTest() {
        List<Payment> payments = paymentService.getAll();
        Assert.assertEquals(1, payments.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updatePaymentTest() {
        Payment payment = paymentService.getPayment(1L);

        Client client = new Client();
        client.setName("Maria Preda");
        client.setEmail("preda.maria91@yahoo.com");
        client.setContact("01448888");
        clientService.create(client);

        Host host = new Host();
        host.setName("Paiu");
        host.setEmail("cosmin.paiu@gmail.com");
        hostService.create(host);

        Property property = new Property();
        property.setName("Hilton");
        property.setTelephone("01478866");
        property.setEmail("contact@hilton.com");
        property.setAddress("Cluj");
        property.setHost(host);
        propertyService.create(property);

        Booking booking = new Booking();
        Date checkInDate = new GregorianCalendar(2019, Calendar.MARCH, 8).getTime();
        booking.setCheckIn(checkInDate);
        Date checkOutDate = new GregorianCalendar(2019, Calendar.MARCH, 25).getTime();
        booking.setCheckOut(checkOutDate);

        booking.setPersonsNo(3);
        booking.setRoomType(RoomType.TRIPLE);
        booking.setRoomsNo(1);
        booking.setBookingDate();
        booking.setClient(client);
        booking.setProperty(property);
        booking.onCreate();
        bookingService.create(booking);

        payment.setAmount(520.0);
        payment.setBooking(booking);
        payment.setPaymentDate();

        Payment expectedPayment = paymentService.update(payment);
        Payment actualPayment = paymentService.getPayment(1L);
        Assert.assertEquals(expectedPayment, actualPayment);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deletePaymentTest() {
        List<Payment> payments = paymentService.getAll();
        int size = payments.size();
        Payment payment = paymentService.getPayment(1L);
        paymentService.delete(payment);
        payments = paymentService.getAll();
        Assert.assertEquals(size - 1, payments.size());
    }
}
