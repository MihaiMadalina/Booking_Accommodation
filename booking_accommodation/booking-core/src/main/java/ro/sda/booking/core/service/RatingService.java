package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    Rating getRating(Long id);

    Rating getRatingByPropertyIdAndClientId(Property propertyId, Client clientId);

    List<Rating> getAll();

    Rating update(Rating rating);

    void delete(Rating rating);
}
