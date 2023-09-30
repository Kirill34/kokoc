package repo;

import model.CharityEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharityEventRepository extends CrudRepository<CharityEvent,Long> {
    public List<CharityEvent> findAllByIdGreaterThan(Long id);
}
