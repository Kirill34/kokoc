package controller;

import model.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.DepartamentRepository;
import repo.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public Long add(String firstName, String secondName, Long departamentId)
    {
        Employee employee = new Employee(firstName,secondName,departamentRepository.findById(departamentId).get());
        employeeRepository.save(employee);
        return employee.getId();
    }
}
