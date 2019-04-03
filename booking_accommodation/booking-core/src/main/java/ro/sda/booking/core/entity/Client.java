package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "booking")
public class Client extends BaseEntity {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "contact_nr", length = 15)
    private String contactNr;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "bank_account", length = 24)
    private String bankAccount;

    @OneToMany
    @JoinColumn(name = "booking_id")
    private Booking bookingId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNr() {
        return contactNr;
    }

    public void setContactNr(String contactNr) {
        this.contactNr = contactNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return super.getId()==(client.getId()) &&
                getFirstName().equals(client.getFirstName()) &&
                getLastName().equals(client.getLastName()) &&
                getContactNr().equals(client.getContactNr()) &&
                getAddress().equals(client.getAddress()) &&
                getBankAccount().equals(client.getBankAccount()) &&
                getBookingId().equals(client.getBookingId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getContactNr(), getAddress(), getBankAccount(), getBookingId());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNr='" + contactNr + '\'' +
                ", address='" + address + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bookingId=" + bookingId +
                '}';
    }
}

