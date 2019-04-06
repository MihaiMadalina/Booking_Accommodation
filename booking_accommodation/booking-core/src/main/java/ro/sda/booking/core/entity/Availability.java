package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "availability", schema = "booking")
public class Availability extends BaseEntity {

    @Column(name = "room_name", length = 50, nullable = false)
    private String roomName;
    @Column(name = "from_date", length = 50, nullable = false)
    private LocalDate fromDate;
    @Column(name = "to_date", length = 50, nullable = false)
    private LocalDate toDate;
    @Column(name = "room_type", length = 50, nullable = false)
    private String roomType;

    @Column(name = "price_double", length = 50)
    private Double priceDouble;
    @Column(name = "price_single", length = 50)
    private Double priceSingle;

    @ManyToOne
    @JoinColumn (name = "property")
    private Property property;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getPriceDouble() {
        return priceDouble;
    }

    public void setPriceDouble(Double priceDouble) {
        this.priceDouble = priceDouble;
    }

    public Double getPriceSingle() {
        return priceSingle;
    }

    public void setPriceSingle(Double priceSingle) {
        this.priceSingle = priceSingle;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Availability)) return false;
        Availability that = (Availability) o;
        return getId()==(that.getId()) &&
                getRoomName().equals(that.getRoomName()) &&
                getRoomName().equals(that.getRoomName()) &&
                getFromDate().equals(that.getFromDate()) &&
                getToDate().equals(that.getToDate()) &&
                getRoomType().equals(that.getRoomType()) &&
                getPriceDouble().equals(that.getPriceDouble()) &&
                getPriceSingle().equals(that.getPriceSingle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoomName(), getRoomName(), getFromDate(), getToDate(), getRoomType(), getPriceDouble(), getPriceSingle());
    }
}
