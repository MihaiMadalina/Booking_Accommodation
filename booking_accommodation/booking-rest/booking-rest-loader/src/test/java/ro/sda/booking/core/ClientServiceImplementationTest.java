package ro.sda.booking.core;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.service.BookingService;
import ro.sda.booking.core.service.ClientService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ClientServiceImplementationTest {

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    @Transactional
    public void createClientTest(){
        Client client = new Client();
        client.setName("Roxana Preda");
        client.setContact("0754332378");
        client.setEmail("roxana.preda@gmail.com");
        Client expectedClient = clientService.create(client);
        Client actualClient = clientService.getClientByName("Roxana Preda");
        Assert.assertEquals(expectedClient, actualClient);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getClientByIdTest() {
        Client expectedClient = clientService.getClientById(1L);
        List<Client> clients = clientService.findAll();
        Assert.assertEquals(expectedClient, clients.get(0));
    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void getClientByNameTest() {
//        Client expectedClient = clientService.getClientByName("Roxana Preda");
//        Client actualClient = clientService.getClientById(1L);
//        Assert.assertEquals(expectedClient, actualClient);
//    }

//    @Test
//    @Transactional
//    @Rollback(false)
//    public void getAllTest() {
//        List<Client> clients = clientService.findAll();
//        Assert.assertEquals(1, clients.size());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void updateClientTest() {
//        Client client = clientService.getClientById(1l);
//        client.setFirstName("Andreea");
//        client.setLastName("Chir");
//        client.setAddress("Str.Bradului, nr.1, Iasi");
//        client.setContactNr("0754334478");
//        client.setBankAccount("476688");
//        Client expectedClient = clientService.update(client);
//        Client actualClient = clientService.getClientById(1L);
//        Assert.assertEquals(expectedClient, actualClient);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void deleteClientTest() {
//        List<Client> clients = clientService.findAll();
//        int size = clients.size();
//        Client client = clientService.getClientById(1L);
//        clientService.delete(client);
//        clients = clientService.findAll();
//        Assert.assertEquals(size - 1, clients.size());
//    }
}
