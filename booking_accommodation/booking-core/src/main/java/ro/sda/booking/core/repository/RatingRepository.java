package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Rating;

public interface RatingRepository extends EntityRepository<Rating> {
    Rating findRatingByPropertyIdAndClientId(Property propertyId, Client clientId);
}
