package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Client;

public interface ClientRepository extends EntityRepository<Client> {

    public Client findByName(String name);
}
