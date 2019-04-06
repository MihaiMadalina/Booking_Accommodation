package ro.sda.booking.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.service.HostService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class HostServiceImplementationTest {

    @Autowired
    private HostService hostService;

    @Test
    @Transactional
    @Rollback(false)
    public void createHostTest() {
        Host host = new Host();
        host.setName("Boz");
        host.setEmail("sorina.boz@gmail.com");
        Host expectedHost = hostService.create(host);
        Host actualHost = hostService.getHostByEmail("sorina.boz@gmail.com");
        Assert.assertEquals(expectedHost, actualHost);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getHostByIdTest() {
        Host expectedHost = hostService.getHost(1L);
        List<Host> hosts = hostService.getAll();
        Assert.assertEquals(expectedHost, hosts.get(0));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getHostByEmailTest() {
        Host expectedHost = hostService.getHostByEmail("sorina.boz@gmail.com");
        Host actualHost = hostService.getHost(1L);
        Assert.assertEquals(expectedHost, actualHost);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllTest() {
        List<Host> hosts = hostService.getAll();
        Assert.assertEquals(1, hosts.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateHostTest() {
        Host host = hostService.getHost(1l);
        host.setName("Roman");
        Host expectedHost = hostService.update(host);
        Host actualHost = hostService.getHost(1L);
        Assert.assertEquals(expectedHost, actualHost);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteHostTest() {
        List<Host> hosts = hostService.getAll();
        int size = hosts.size();
        Host host = hostService.getHost(1L);
        hostService.delete(host);
        hosts = hostService.getAll();
        Assert.assertEquals(size - 1, hosts.size());
    }
}
