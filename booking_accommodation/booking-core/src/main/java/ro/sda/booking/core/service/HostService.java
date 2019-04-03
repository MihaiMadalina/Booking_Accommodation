package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Host;

import java.util.List;

public interface HostService {
    Host create(Host host);

    Host getHost(Long id);

    Host getHostByEmail(String email);

    List<Host> getAll();

    Host update(Host host);

    void delete(Host host);
}
