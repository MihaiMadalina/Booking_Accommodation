package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Accommodation;

public interface AccommodationRepository extends EntityRepository<Accommodation> {
    Accommodation findByAccomodationName(String name);
}
