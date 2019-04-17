package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "payments", schema = "booking")
public class Payment extends BaseEntity {

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private String paymentDate;

    @OneToOne
    @JoinColumn(name = "booking", nullable = false)
    private Booking booking;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate() {

        paymentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return super.getId()==(payment.getId()) &&
                getAmount().equals(payment.getAmount()) &&
                getPaymentDate().equals(payment.getPaymentDate()) &&
                getBooking().equals(payment.getBooking());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getPaymentDate(), getBooking(), getId());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + getId() +
                "amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", booking=" + booking +
                '}';
    }
}
