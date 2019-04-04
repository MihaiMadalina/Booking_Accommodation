package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Accommodation;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.service.AccommodationService;
import ro.sda.booking.core.service.HostService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class AccommodationServiceImplementationTest {

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private HostService hostService;

    @Test
    @Transactional
    @Rollback(false)
    public void createAccommodationTest(){
        Host host = new Host();
        host.setLastName("Boz");
        host.setFirstName("Sorina");
        host.setCity("Iasi");
        host.setEmail("sorina.boz@gmail.com");
        host.setPhoneNumber("766566342");
        host = hostService.create(host);
        Accommodation expectedAccommodation = new Accommodation();
        expectedAccommodation.setAccomodationName("Rixos");
        expectedAccommodation.setAccommodationType("Hotel");
        expectedAccommodation.setAddress("London");
        expectedAccommodation.setNoOfRooms(100);
        expectedAccommodation.setPrice(100.00);
        expectedAccommodation.setHost(host);
        expectedAccommodation = accommodationService.create(expectedAccommodation);
        Accommodation actualAccommodation = accommodationService.getAccommodationByName("Rixos");
        Assert.assertEquals(expectedAccommodation, actualAccommodation);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAccommodationByIdTest(){
        Accommodation expectedAccommodation = accommodationService.getAccommodation(1L);
        Accommodation actualAccommodation = accommodationService.getAccommodationByName("Rixos");
        Assert.assertEquals(expectedAccommodation, actualAccommodation);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAccommodationByNameTest(){
        Accommodation expectedAccommodation = accommodationService.getAccommodationByName("Rixos");
        Accommodation actualAccommodation = accommodationService.getAccommodation(1L);
        Assert.assertEquals(expectedAccommodation, actualAccommodation);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllAccommodationsTest(){
        List<Accommodation> accommodations = accommodationService.getAll();
        Assert.assertEquals(1, accommodations.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateAccommodationTest(){
        Accommodation expectedAccommodation = accommodationService.getAccommodation(1L);
        expectedAccommodation.setAccomodationName("Havana");
        expectedAccommodation = accommodationService.update(expectedAccommodation);
        Accommodation actualAccommodation = accommodationService.getAccommodationByName("Havana");
        Assert.assertEquals(expectedAccommodation, actualAccommodation);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteAccommodationTest(){
        List<Accommodation> accommodations = accommodationService.getAll();
        int size = accommodations.size();
        Accommodation accommodation = accommodationService.getAccommodation(1L);
        accommodationService.delete(accommodation);
        accommodations = accommodationService.getAll();
        Assert.assertEquals(size-1, accommodations.size());
    }



}
