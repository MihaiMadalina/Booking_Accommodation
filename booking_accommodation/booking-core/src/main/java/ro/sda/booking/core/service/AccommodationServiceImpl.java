package ro.sda.booking.core.service;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Accommodation;
import ro.sda.booking.core.repository.AccommodationRepository;

import java.util.List;

@Service("accommodationService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;


    @Override
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation getAccommodation(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation getAccommodationByName(String name) {
        return accommodationRepository.findByAccomodationName(name);
    }

    @Override
    public List<Accommodation> getAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation update(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public void delete(Accommodation accommodation) {
        accommodationRepository.delete(accommodation);

    }
}
