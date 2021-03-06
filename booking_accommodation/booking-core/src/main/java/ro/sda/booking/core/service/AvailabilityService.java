package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Availability;
import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.List;

public interface AvailabilityService {
    Availability create(Availability availability);

    Availability getAvailability(Long id);

    Availability findAvailabilityByPropertyIdAndRoomName(Long id, String roomName);

    List<Availability> getAll();

    Availability update(Availability availability);

    void delete(Availability availability);

    boolean existsAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate);

    List<Availability> findAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual (Date fromDate, Date toDate);

}
