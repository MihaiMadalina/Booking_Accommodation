package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Client;

import java.util.List;

public interface ClientService {

    Client create(Client client);

    Client update(Client client);

    Client getClientById(Long id);

    Client getClientByName(String name);

    List<Client> findAll();

    void delete(Client client);
}
