package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Client;

public interface ClientRepository extends EntityRepository<Client> {

    public Client createClient(Client client);

    public Client updateClient(Client client);

    public Client findByLastName(String lastName);

    public Long deleteById(Long id);

}
