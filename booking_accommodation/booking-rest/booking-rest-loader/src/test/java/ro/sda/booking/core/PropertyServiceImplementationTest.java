package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.service.PropertyService;
import ro.sda.booking.core.service.HostService;
import ro.sda.booking.core.service.RatingService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PropertyServiceImplementationTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private HostService hostService;

    @Test
    @Transactional
    @Rollback(false)
    public void createPropertyTest(){
        Host host = new Host();
        host.setName("Tatarcan");
        host.setEmail("maria.tatarcan@gmail.com");
        host = hostService.create(host);
        Property expectedProperty = new Property();
        expectedProperty.setName("Hotel Alexandra");
        expectedProperty.setEmail("hotel@alexandra.com");
        expectedProperty.setTelephone("506546874");
        expectedProperty.setAddress("Norway");
        expectedProperty.setHost(host);
        expectedProperty = propertyService.create(expectedProperty);
        Property actualProperty = propertyService.getPropertyByName("Hotel Alexandra");
        Assert.assertEquals(expectedProperty, actualProperty);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getPropertyByIdTest(){
        Property expectedProperty = propertyService.getProperty(4L);
        Property actualProperty = propertyService.getPropertyByName("Bucium");
        Assert.assertEquals(expectedProperty, actualProperty);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getPropertyByNameTest(){
        Property expectedProperty = propertyService.getPropertyByName("Bucium");
        Property actualProperty = propertyService.getProperty(4L);
        Assert.assertEquals(expectedProperty, actualProperty);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllPropertiesTest(){
        List<Property> properties = propertyService.getAll();
        Assert.assertEquals(4, properties.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updatePropertiesTest(){
        Property expectedProperty = propertyService.getProperty(3L);
        expectedProperty.setName("Havana");
        expectedProperty.setEmail("contact@havana.com");
        expectedProperty.setTelephone("6565365");
        expectedProperty = propertyService.update(expectedProperty);
        Property actualProperty = propertyService.getPropertyByName("Havana");
        Assert.assertEquals(expectedProperty, actualProperty);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deletePropertyTest(){
        List<Property> properties = propertyService.getAll();
        int size = properties.size();
        Property property = propertyService.getProperty(5L);
        propertyService.delete(property);
        properties = propertyService.getAll();
        Assert.assertEquals(size-1, properties.size());
    }
}
