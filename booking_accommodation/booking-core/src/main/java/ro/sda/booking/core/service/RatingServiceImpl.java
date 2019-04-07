package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.repository.RatingRepository;

import java.util.List;

@Service("ratingService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRating(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating getRatingByPropertyIdAndClientId(Property propertyId, Client clientId) {
        return ratingRepository.findRatingByPropertyIdAndClientId(propertyId, clientId);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating update(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }
}
