package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.repository.PropertyRepository;

import java.util.List;

@Service("propertyService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property create(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property getProperty(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public Property getPropertyByName(String name) {
        return propertyRepository.findPropertyByName(name);
    }

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property update(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public void delete(Property property) {
        propertyRepository.delete(property);

    }
}
