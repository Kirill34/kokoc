package repo;

import model.CharitySportTransaction;
import model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharitySportTransactionRepository extends CrudRepository<CharitySportTransaction, Long> {
    public List<CharitySportTransaction> findAllBySportActionEmployeeId(Long id);
    public List<CharitySportTransaction> findAllBySportActionEmployee(Employee employee);

    public List<CharitySportTransaction> findAllBySportActionEmployeeDepartamentId(Long id);
}
