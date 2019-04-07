package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Availability;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends EntityRepository<Availability> {
    Availability  findAvailabilityByPropertyIdAndRoomName(Long id, String roomName);
    List<Availability> findAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(LocalDate fromDate, LocalDate toDate);
}
