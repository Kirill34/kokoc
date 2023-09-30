package controller;

import model.Employee;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("/{id}/view")
    public ModelAndView getView(Model model, @PathVariable Long id)
    {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("firstName",employee.getFirstName());
        model.addAttribute("secondName",employee.getSecondName());
        model.addAttribute("id",employee.getId());
        return new ModelAndView("employeeView");
    }
}
