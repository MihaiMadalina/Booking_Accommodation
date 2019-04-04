package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accommodations", schema = "booking")
public class Accommodation extends BaseEntity {

    @Column(name = "accomodation_name", length = 50, nullable = false)
    private String accomodationName;

    @Column(name = "accommodation_type", length = 50, nullable = false)
    private String accommodationType;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "number_of_rooms", length = 50, nullable = false)
    private int noOfRooms;

    @Column(name = "price", length = 50, nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "host", nullable = false)
    private Host host;


    public String getAccomodationName() {
        return accomodationName;
    }

    public void setAccomodationName(String accomodationName) {
        this.accomodationName = accomodationName;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accommodation)) return false;
        Accommodation that = (Accommodation) o;
        return getId() == that.getId() &&
                getNoOfRooms() == that.getNoOfRooms() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                getAccomodationName().equals(that.getAccomodationName()) &&
                getAccommodationType().equals(that.getAccommodationType()) &&
                getAddress().equals(that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccomodationName(), getAccommodationType(), getAddress(), getNoOfRooms(), getPrice());
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "accomodationName='" + accomodationName + '\'' +
                ", accommodationType='" + accommodationType + '\'' +
                ", address='" + address + '\'' +
                ", noOfRooms=" + noOfRooms +
                ", price=" + price +
                '}';
    }
}
