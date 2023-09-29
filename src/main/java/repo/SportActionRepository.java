package repo;

import model.Employee;
import model.SportAction;
import model.SportKind;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SportActionRepository extends CrudRepository<SportAction,Long> {
    public List<SportAction> findAllByEmployeeId(Long id);
    public List<SportAction> findAllByEmployee(Employee employee);
    public List<SportAction> findAllBySportKind(SportKind sportKind);
    public List<SportAction> findAllBySportKindId(Long id);
}
