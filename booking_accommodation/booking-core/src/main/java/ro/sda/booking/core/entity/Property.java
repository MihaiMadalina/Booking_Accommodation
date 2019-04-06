package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "properties", schema = "booking")
public class Property extends BaseEntity {

    @Column(name = "property_name", length = 50, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "telephone", length = 50, nullable = false)
    private String telephone;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "host", nullable = false)
    private Host host;


    public String getName() {
        return name;
    }

    public void setName(String accomodationName) {
        this.name = accomodationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return getId()==(property.getId()) &&
                getName().equals(property.getName()) &&
                getAddress().equals(property.getAddress()) &&
                getTelephone().equals(property.getTelephone()) &&
                getEmail().equals(property.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getTelephone(), getEmail());
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
