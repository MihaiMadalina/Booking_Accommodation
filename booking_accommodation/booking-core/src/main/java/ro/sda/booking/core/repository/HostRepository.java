package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Host;

public interface HostRepository extends EntityRepository<Host> {
    Host findByEmail(String email);
}
