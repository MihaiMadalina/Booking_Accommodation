package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Accommodation;

import java.util.List;

public interface AccommodationService {
    Accommodation create(Accommodation accommodation);

    Accommodation getAccommodation(Long id);

    Accommodation getAccommodationByName (String name);

    List<Accommodation> getAll();

    Accommodation update(Accommodation accommodation);

    void delete(Accommodation accommodation);
}
