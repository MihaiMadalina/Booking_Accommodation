package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment create(Payment payment);

    Payment getPayment(Long id);

    List<Payment> getAll();

    Payment update(Payment payment);

    void delete(Payment payment);
}
