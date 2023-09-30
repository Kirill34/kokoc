package repo;

import model.Departament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartamentRepository extends CrudRepository<Departament,Long> {
    public List<Departament> findAllByIdGreaterThan(Long id);
}
