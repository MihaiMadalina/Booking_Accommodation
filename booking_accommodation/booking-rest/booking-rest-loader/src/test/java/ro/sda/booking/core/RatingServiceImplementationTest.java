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
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.HostService;
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

    @Autowired
    private HostService hostService;

    @Test
    @Rollback(false)
    @Transactional
    public void createRatingTest(){

        Client client = new Client();
        client.setName("Ion Popa");
        client.setEmail("ion.popa85@yahoo.com");
        client.setContact("047666999");
        clientService.create(client);

        Host host = new Host();
        host.setName("Cristian");
        host.setEmail("cuciurhanc@gmail.com");
        hostService.create(host);

        Property property = new Property();
        property.setName("Bellaria");
        property.setTelephone("07458866");
        property.setEmail("contact.bellaria@outlook.com");
        property.setAddress("Timisoara");
        property.setHost(host);
        propertyService.create(property);

        Rating expectedRating = new Rating();
        expectedRating.setRating(5);
        expectedRating.setComment("Perfect accommodation. Very friendly staff, good food.");
        expectedRating.setClientId(client);
        expectedRating.setPropertyId(property);
        ratingService.create(expectedRating);

        List<Rating> ratings = ratingService.getAll();
        Assert.assertEquals(expectedRating, ratings.get(0));
    }

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
        Rating rating = ratingService.getRating(1L);

        Client client = new Client();
        client.setName("Cristina Enache");
        client.setEmail("cristina.enache@gmail.com");
        client.setContact("0487755");
        clientService.create(client);

        Host host = new Host();
        host.setName("George");
        host.setEmail("mocanugeorge@gmail.com");
        hostService.create(host);

        Property property = new Property();
        property.setName("International");
        property.setTelephone("0477856");
        property.setEmail("contact.international@yahoo.com");
        property.setAddress("Oradea");
        property.setHost(host);
        propertyService.create(property);

        rating.setComment("Very disappointing experience. I do not recommend this place.");
        rating.setRating(1);
        rating.setClientId(client);
        rating.setPropertyId(property);
        Rating expectedRating = ratingService.update(rating);
        Rating actualRating = ratingService.getRating(1L);
        Assert.assertEquals(expectedRating, actualRating);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteRatingTest() {
        List<Rating> ratings = ratingService.getAll();
        int size = ratings.size();
        Rating rating = ratingService.getRating(1L);
        ratingService.delete(rating);
        ratings = ratingService.getAll();
        Assert.assertEquals(size - 1, ratings.size());
    }
}
