package repo;

import model.Departament;
import org.springframework.data.repository.CrudRepository;

public interface DepartamentRepository extends CrudRepository<Departament,Long> {
}
