package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Availability;

public interface AvailabilityRepository extends EntityRepository<Availability> {
    Availability  findAvailabilityByPropertyIdAndRoomName(Long id, String roomName);
}
