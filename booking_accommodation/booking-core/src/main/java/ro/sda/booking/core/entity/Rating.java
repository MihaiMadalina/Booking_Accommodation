package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ratings", schema = "booking")
public class Rating extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "property", nullable = false)
    private Property propertyId;

    @Column(name = "comments")
    private String comment;

    @Column(name = "rating", length = 1)
    private int rating;

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return super.getId()==(rating1.getId()) &&
                getRating() == rating1.getRating() &&
                getClientId().equals(rating1.getClientId()) &&
                getPropertyId().equals(rating1.getPropertyId()) &&
                getComment().equals(rating1.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getPropertyId(), getComment(), getRating(), getId());
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + getId() +
                ", clientId=" + clientId +
                ", propertyId=" + propertyId +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
