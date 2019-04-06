package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.PropertyService;
import ro.sda.booking.core.service.RatingService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class RatingServiceImplementationTest {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PropertyService propertyService;

    @Test
    @Rollback(false)
    @Transactional
    public void createRatingTest(){
        Rating rating = new Rating();
        Property property = new Property();
        Client client = new Client();
        rating.setPropertyId(propertyService.getProperty(1L));
        rating.setClientId(clientService.getClientById(2L));
        rating.setComment("Very nice and clean. Friendly staff.");
        rating.setRating(4);
        ratingService.create(rating);
        Assert.assertNotNull(rating);
    }

//    @Test
//    @Transactional
//    @Rollback(false)
//    public void getRatingByPropertyIdAndClientIdTest() {
//        Rating rating = new Rating();
//        Client clientId = clientService.getClientById(2L);
//        Property propertyId = propertyService.getProperty(1L);
//        Rating expectedRating = ratingService.getRatingByPropertyIdAndClientId(propertyId, clientId);
//        Rating actualRating = ratingService.getRating(1L);
//        Assert.assertEquals(expectedRating, actualRating);
//    }

    @Test
    @Transactional
    @Rollback(false)
    public void getRatingByIdTest() {
        Rating expectedRating = ratingService.getRating(1L);
        List<Rating> ratings = ratingService.getAll();
        Assert.assertEquals(expectedRating, ratings.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllRatingsTest() {
        List<Rating> ratings = ratingService.getAll();
        Assert.assertEquals(1, ratings.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateRatingTest() {
        Rating rating = ratingService.getRating(2L);
        rating.setPropertyId(propertyService.getProperty(1L));
        rating.setClientId(clientService.getClientById(2L));
        rating.setComment("Very disappointing experience. I do not recommend this place.");
        rating.setRating(1);
        Rating expectedRating = ratingService.update(rating);
        Rating actualRating = ratingService.getRating(2L);
        Assert.assertEquals(expectedRating, actualRating);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteRatingTest() {
        List<Rating> ratings = ratingService.getAll();
        int size = ratings.size();
        Rating rating = ratingService.getRating(3L);
        ratingService.delete(rating);
        ratings = ratingService.getAll();
        Assert.assertEquals(size - 1, ratings.size());
    }
}
