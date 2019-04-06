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
import ro.sda.booking.core.entity.Payment;
import ro.sda.booking.core.service.BookingService;
import ro.sda.booking.core.service.PaymentService;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PaymentServiceImplementationTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    @Test
    @Rollback(false)
    @Transactional
    public void createPaymentTest(){
        Payment payment = new Payment();
        Booking booking = new Booking();
        payment.setAmount(120.0);
        payment.setBooking(bookingService.getBooking(2L));
        LocalDate paymentDate = LocalDate.of(2019, 4, 6);
        payment.setPaymentDate(paymentDate);
        paymentService.create(payment);
        Assert.assertNotNull(payment);
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
        Payment payment = paymentService.getPayment(2L);
        payment.setAmount(2501.0);
        payment.setBooking(bookingService.getBooking(1L));
        LocalDate paymentDate = LocalDate.of(2020, 1, 6);
        payment.setPaymentDate(paymentDate);
        Payment expectedPayment = paymentService.update(payment);
        Payment actualPayment = paymentService.getPayment(2L);
        Assert.assertEquals(expectedPayment, actualPayment);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deletePaymentTest() {
        List<Payment> payments = paymentService.getAll();
        int size = payments.size();
        Payment payment = paymentService.getPayment(3L);
        paymentService.delete(payment);
        payments = paymentService.getAll();
        Assert.assertEquals(size - 1, payments.size());
    }
}
