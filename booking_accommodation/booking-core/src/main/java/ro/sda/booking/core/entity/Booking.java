package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "bookings", schema = "booking")
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "property", nullable = false)
    private Property property;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Column(name = "no_of_persons", nullable = false)
    private int personsNo;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "no_of_rooms", nullable = false)
    private int roomsNo;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getPersonsNo() {
        return personsNo;
    }

    public void setPersonsNo(int personsNo) {
        this.personsNo = personsNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomsNo() {
        return roomsNo;
    }

    public void setRoomsNo(int roomsNo) {
        this.roomsNo = roomsNo;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return super.getId()==(booking.getId()) &&
                getPersonsNo() == booking.getPersonsNo() &&
                getRoomsNo() == booking.getRoomsNo() &&
                getClient().equals(booking.getClient()) &&
                getProperty().equals(booking.getProperty()) &&
                getCheckIn().equals(booking.getCheckIn()) &&
                getCheckOut().equals(booking.getCheckOut()) &&
                getRoomType().equals(booking.getRoomType()) &&
                getBookingDate().equals(booking.getBookingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient(), getProperty(), getCheckIn(), getCheckOut(), getPersonsNo(), getRoomType(), getRoomsNo(), getBookingDate(), getId());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + getId() +
                ", client=" + client +
                ", property=" + property +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", personsNo=" + personsNo +
                ", roomType='" + roomType + '\'' +
                ", roomsNo=" + roomsNo +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
