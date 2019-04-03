package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "hosts", schema = "booking")
public class Host extends BaseEntity {

    @Column(name="last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name="first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name="city", length = 50, nullable = false)
    private String city;

    @Column(name="phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(name="email", length = 50, nullable = false, unique = true)
    private String email;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host)) return false;
        Host host = (Host) o;
        return getId()==(host.getId()) &&
                getLastName().equals(host.getLastName()) &&
                getFirstName().equals(host.getFirstName()) &&
                getCity().equals(host.getCity()) &&
                getPhoneNumber().equals(host.getPhoneNumber()) &&
                getEmail().equals(host.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastName(), getFirstName(), getCity(), getPhoneNumber(), getEmail());
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + getId() +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
