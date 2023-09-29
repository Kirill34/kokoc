package repo;

import model.SportKind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SportKindRepository extends CrudRepository<SportKind, Long> {
    public List<SportKind> findAllByIdGreaterThan(Long value);
}
