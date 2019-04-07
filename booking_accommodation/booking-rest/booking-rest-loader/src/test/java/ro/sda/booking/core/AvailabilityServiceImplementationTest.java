package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.service.AvailabilityService;
import ro.sda.booking.core.service.HostService;
import ro.sda.booking.core.service.PropertyService;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class AvailabilityServiceImplementationTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private HostService hostService;

    @Test
    @Transactional
    @Rollback(false)
    public void createAvailabilityTest(){
        Host host = new Host();
        host.setName("Popa");
        host.setEmail("sorina.popa@gmail.com");
        hostService.create(host) ;
       Property property = new Property();
       property.setName("Radisson Blue");
       property.setTelephone("436564");
       property.setEmail("contact@radisson.com");
       property.setAddress("London");
       property.setHost(host);
       propertyService.create(property);
        Availability expectedAvailability = new Availability();
        expectedAvailability.setProperty(property);
        expectedAvailability.setRoomName("Room ABBA");
        expectedAvailability.setRoomType("Single");
        expectedAvailability.setPriceSingle(100.00);
        LocalDate fromDate = LocalDate.of(2019, 4, 1);
        LocalDate toDate = LocalDate.of(2019, 4, 30);
        expectedAvailability.setFromDate(fromDate);
        expectedAvailability.setToDate(toDate);
        availabilityService.create(expectedAvailability);
        List<Availability> availabilities = availabilityService.getAll();
        Assert.assertEquals(expectedAvailability, availabilities.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAvailabilityByIdTest(){
        Availability expectedAvailability = availabilityService.getAvailability(6L);
        Availability actualAvailability = availabilityService.findAvailabilityByPropertyIdAndRoomName(4L, "Room ABBA");
        Assert.assertEquals(expectedAvailability, actualAvailability);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAvailabilityByPropertyIdAndRoomNameTest(){
        Availability expectedAvailability = availabilityService.findAvailabilityByPropertyIdAndRoomName(4L, "Room ABBA");
        Availability actualAvailability = availabilityService.getAvailability(6L);
        Assert.assertEquals(expectedAvailability, actualAvailability);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void getAllAvailabilitiesTest(){
        List<Availability> availabilities = availabilityService.getAll();
        Assert.assertEquals(1, availabilities.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateAvailabilitiesTest(){
        Availability expectedAvailability = availabilityService.getAvailability(6L);
        expectedAvailability.setRoomType("Double");
        expectedAvailability.setPriceSingle(120.00);
        expectedAvailability.setPriceDouble(180.00);
        expectedAvailability.setRoomName("Room A");
        LocalDate fromDate = LocalDate.of(2019, 04, 2);
        LocalDate toDate = LocalDate.of(2019, 04, 20);
        expectedAvailability.setFromDate(fromDate);
        expectedAvailability.setToDate(toDate);
        expectedAvailability = availabilityService.update(expectedAvailability);
        Availability actualAvailability = availabilityService.findAvailabilityByPropertyIdAndRoomName(4L, "Room A");
        Assert.assertEquals(expectedAvailability, actualAvailability);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteAvailabilityTest(){
        List<Availability> availabilities = availabilityService.getAll();
        int size = availabilities.size();
        Availability availability = availabilityService.getAvailability(6L);
        availabilityService.delete(availability);
        availabilities = availabilityService.getAll();
        Assert.assertEquals(size-1, availabilities.size());
    }

}
