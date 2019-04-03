package ro.sda.booking.core.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityRepository <T extends BaseEntity> extends JpaRepository<T,Long> {

    public T findById(long id);

    public T save(T entity);

    List<T> findAll();

    void delete(T entity);

}
