package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.repository.HostRepository;

import java.util.List;

@Service("productCategoryService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository hostRepository;

    @Override
    public Host create(Host host) {

        return hostRepository.save(host); // have all CRUD methods by extending JpaRepository
    }

    @Override
    public Host getHost(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Host getHostByEmail(String email) {

        return hostRepository.findByEmail(email);
    }

    @Override
    public List<Host> getAll() {

        return hostRepository.findAll();
    }

    @Override
    public Host update(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public void delete(Host host) {
        hostRepository.delete(host);

    }
}
