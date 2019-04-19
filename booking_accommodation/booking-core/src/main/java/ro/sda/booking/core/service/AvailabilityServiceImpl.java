package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.repository.AvailabilityRepository;
import java.util.Date;
import java.util.List;

@Service("availabilityService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public Availability create(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public Availability getAvailability(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public Availability findAvailabilityByPropertyIdAndRoomName(Long id, String roomName) {
        return availabilityRepository.findAvailabilityByPropertyIdAndRoomName (id, roomName);
    }

    @Override
    public List<Availability> getAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Availability update(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public void delete(Availability availability) {
        availabilityRepository.delete(availability);
    }

    @Override
    public boolean existsAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate) {
        return availabilityRepository.existsAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate, toDate);
    }

    @Override
    public List<Availability> findAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate) {
        return availabilityRepository.findAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate, toDate);
    }


}
