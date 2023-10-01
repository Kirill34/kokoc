package repo;

import entities.DepatamentEarn;
import model.CharitySportTransaction;
import model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharitySportTransactionRepository extends CrudRepository<CharitySportTransaction, Long> {
    public List<CharitySportTransaction> findAllBySportActionEmployeeId(Long id);
    public List<CharitySportTransaction> findAllBySportActionEmployee(Employee employee);

    public List<CharitySportTransaction> findAllBySportActionEmployeeDepartamentId(Long id);

    public List<CharitySportTransaction> findAllBySportAction_SportKindId(Long id);

    /*@Query("select new com.example.kokoc.entities.DepatamentEarn(c.sportAction.employee.departament.id, c.sportAction.employee.departament.name, sum(c.money)) from CharitySportTransaction c group by c.sportAction.employee.departament.id")
    public List<DepatamentEarn> getSumForDepartaments();*/
}
