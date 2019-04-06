package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "hosts", schema = "booking")
public class Host extends BaseEntity {

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="email", length = 50, nullable = false, unique = true)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String lastName) {
        this.name = lastName;
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
                getName().equals(host.getName()) &&
                getEmail().equals(host.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail());
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
