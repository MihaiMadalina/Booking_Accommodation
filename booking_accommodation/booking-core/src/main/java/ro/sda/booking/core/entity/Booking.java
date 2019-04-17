package ro.sda.booking.core.entity;
import ro.sda.booking.core.base.BaseEntity;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings", schema = "booking")
public class Booking extends BaseEntity {

    @Column(name = "booking_no", nullable = false, unique = true)
    private String bookingNo;

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "property", nullable = false)
    private Property property;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_in", nullable = false)
    private Date checkIn;

    @Column(name = "check_out", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @Column(name = "no_of_persons", nullable = false)
    private int personsNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "no_of_rooms", nullable = false)
    private int roomsNo;

    @Column(name = "booking_date", nullable = false)
    private String bookingDate;

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    @PrePersist
    public void onCreate() {
        setBookingNo(RandomStringUtils.randomAlphanumeric(8));
        }

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


    public int getPersonsNo() {
        return personsNo;
    }

    public void setPersonsNo(int personsNo) {
        this.personsNo = personsNo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomsNo() {
        return roomsNo;
    }

    public void setRoomsNo(int roomsNo) {
        this.roomsNo = roomsNo;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate() {

        bookingDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public Date getCheckIn() {
        return checkIn;
    }


    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return super.getId()==(booking.getId()) &&
                getBookingNo() == booking.getBookingNo() &&
                getPersonsNo() == booking.getPersonsNo() &&
                getRoomsNo() == booking.getRoomsNo() &&
                getClient().equals(booking.getClient()) &&
                getProperty().equals(booking.getProperty()) &&
                getCheckIn().equals(booking.getCheckIn()) &&
                getCheckOut().equals(booking.getCheckOut()) &&
                getRoomType() == booking.getRoomType() &&
                getBookingDate().equals(booking.getBookingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingNo(), getClient(), getProperty(), getCheckIn(), getCheckOut(), getPersonsNo(), getRoomType(), getRoomsNo(), getBookingDate(), getId());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + getId() +
                ", bookingNo=" + bookingNo +
                ", client=" + client +
                ", property=" + property +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", personsNo=" + personsNo +
                ", roomType=" + roomType +
                ", roomsNo=" + roomsNo +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
