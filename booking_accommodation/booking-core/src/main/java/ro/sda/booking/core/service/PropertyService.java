package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Property;

import java.util.List;

public interface PropertyService {
    Property create(Property property);

    Property getProperty(Long id);

    Property getPropertyByName(String name);

    List<Property> getAll();

    Property update(Property property);

    void delete(Property property);
}
