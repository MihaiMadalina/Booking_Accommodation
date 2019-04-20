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
import ro.sda.booking.core.entity.RoomType;
import ro.sda.booking.core.service.AvailabilityService;
import ro.sda.booking.core.service.HostService;
import ro.sda.booking.core.service.PropertyService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        host.setName("Boz");
        host.setEmail("ionela.boz@gmail.com");
        hostService.create(host);
        Property property = new Property();
        property.setName("Roxis");
        property.setTelephone("865498974");
        property.setEmail("contact@rixos.com");
        property.setAddress("London");
        property.setHost(host);
        propertyService.create(property);
        Availability expectedAvailability = new Availability();
        expectedAvailability.setProperty(property);
        expectedAvailability.setRoomName("Room1");
        expectedAvailability.setRoomType(RoomType.SINGLE);
        expectedAvailability.setPriceSingle(100.00);
        Date fromDate = new GregorianCalendar(2019, Calendar.APRIL, 1).getTime();
        Date toDate = new GregorianCalendar(2019, Calendar.APRIL, 30).getTime();
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
        Availability expectedAvailability = availabilityService.getAvailability(1L);
        List<Availability> availabilities = availabilityService.getAll();
        Assert.assertEquals(expectedAvailability, availabilities.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAvailabilityByPropertyIdAndRoomNameTest(){
        Availability expectedAvailability = availabilityService.findAvailabilityByPropertyIdAndRoomName(8L, "Room1");
        Availability actualAvailability = availabilityService.getAvailability(25L);
        Assert.assertEquals(expectedAvailability, actualAvailability);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void getAllAvailabilitiesTest(){
        List<Availability> availabilities = availabilityService.getAll();
        Assert.assertEquals(4, availabilities.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateAvailabilitiesTest(){
        Availability expectedAvailability = availabilityService.getAvailability(3L);
        expectedAvailability.setRoomType(RoomType.DOUBLE);
        expectedAvailability.setPriceSingle(120.00);
        expectedAvailability.setPriceDouble(180.00);
        expectedAvailability.setRoomName("Room A");
        Date fromDate = new GregorianCalendar(2019, Calendar.APRIL, 2).getTime();
        Date toDate = new GregorianCalendar(2019, Calendar.APRIL, 20).getTime();
        expectedAvailability.setFromDate(fromDate);
        expectedAvailability.setToDate(toDate);
        expectedAvailability = availabilityService.update(expectedAvailability);
        Availability actualAvailability = availabilityService.findAvailabilityByPropertyIdAndRoomName(3L, "Room A");
        Assert.assertEquals(expectedAvailability, actualAvailability);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteAvailabilityTest(){
        List<Availability> availabilities = availabilityService.getAll();
        int size = availabilities.size();
        Availability availability = availabilityService.getAvailability(5L);
        availabilityService.delete(availability);
        availabilities = availabilityService.getAll();
        Assert.assertEquals(size-1, availabilities.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findAvailabilityFromDateToDate(){
        Property property1 = propertyService.getProperty(2L);
        Property property2 = propertyService.getProperty(4L);
        Availability expectedAvailability1 = new Availability();
        expectedAvailability1.setProperty(property1);
        Date fromDate1 = new GregorianCalendar(2019, Calendar.APRIL, 7).getTime();
        Date toDate1 = new GregorianCalendar(2019, Calendar.APRIL, 30).getTime();
        expectedAvailability1.setFromDate(fromDate1);
        expectedAvailability1.setToDate(toDate1);
        expectedAvailability1.setRoomName("Room 1");
        expectedAvailability1.setRoomType(RoomType.SINGLE);
        expectedAvailability1.setPriceSingle(100.00);
        expectedAvailability1 = availabilityService.create(expectedAvailability1);
        Availability expectedAvailability2 = new Availability();
        expectedAvailability2.setProperty(property2);
        expectedAvailability2.setRoomName("Room 1");
        expectedAvailability2.setRoomType(RoomType.DOUBLE);
        expectedAvailability2.setPriceSingle(120.00);
        expectedAvailability2.setPriceDouble(160.00);
        Date fromDate2 = new GregorianCalendar(2019, Calendar.APRIL, 6).getTime();
        Date toDate2 = new GregorianCalendar(2019, Calendar.APRIL, 30).getTime();
        expectedAvailability2.setFromDate(fromDate2);
        expectedAvailability2.setToDate(toDate2);
        expectedAvailability2 = availabilityService.create(expectedAvailability2);
        Availability expectedAvailability3 = new Availability();
        expectedAvailability3.setProperty(property1);
        expectedAvailability3.setRoomName("Room 2");
        expectedAvailability3.setRoomType(RoomType.DOUBLE);
        expectedAvailability3.setPriceSingle(100.00);
        expectedAvailability3.setPriceDouble(150.00);
        Date fromDate3 = new GregorianCalendar(2019, Calendar.APRIL, 9).getTime();
        Date toDate3 = new GregorianCalendar(2019, Calendar.APRIL, 29).getTime();
        expectedAvailability3.setFromDate(fromDate3);
        expectedAvailability3.setToDate(toDate3);
        expectedAvailability3 = availabilityService.create(expectedAvailability3);
        Availability expectedAvailability4 = new Availability();
        expectedAvailability4.setProperty(property2);
        expectedAvailability4.setRoomName("Room 3");
        expectedAvailability4.setRoomType(RoomType.DOUBLE);
        expectedAvailability4.setPriceSingle(100.00);
        expectedAvailability4.setPriceDouble(150.00);
        Date fromDate4 = new GregorianCalendar(2019, Calendar.APRIL, 9).getTime();
        Date toDate4 = new GregorianCalendar(2019, Calendar.MAY, 1).getTime();
        expectedAvailability4.setFromDate(fromDate4);
        expectedAvailability4.setToDate(toDate4);
        expectedAvailability4 = availabilityService.create(expectedAvailability4);
        List<Availability>availabilities = availabilityService.findAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate1, toDate1);
        Assert.assertEquals(2, availabilities.size());
        Assert.assertEquals(expectedAvailability1, availabilities.get(0));
        Assert.assertEquals(expectedAvailability3, availabilities.get(1));

    }

    @Test
    @Rollback(false)
    @Transactional
    public void isAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(){
        Date fromDate = new GregorianCalendar(2019, Calendar.MAY, 9).getTime();
        Date toDate = new GregorianCalendar(2019, Calendar.JUNE, 1).getTime();
        boolean isAvailable;
        isAvailable = availabilityService.existsAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate, toDate);
        Assert.assertEquals(true, isAvailable);

    }


}
