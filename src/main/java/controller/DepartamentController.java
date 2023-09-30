package controller;

import model.Departament;
import model.Employee;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.DepartamentRepository;
import repo.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/departament")
public class DepartamentController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public Long add(String name)
    {
        Departament departament = new Departament(name);
        departamentRepository.save(departament);
        return departament.getId();
    }

    @GetMapping("/all")
    public List<Departament> getAll()
    {
        return departamentRepository.findAllByIdGreaterThan(0L);
    }

    @GetMapping("/view")
    public ModelAndView getPageForAllSports(Model model)
    {
        return new ModelAndView("allDepartamentsView");
    }

    @GetMapping("/{id}/view")
    public ModelAndView getPageForDepartament(Model model, @PathVariable Long id)
    {
        Departament departament = departamentRepository.findById(id).get();
        model.addAttribute("name",departament.getName());
        model.addAttribute("id",departament.getId());

        List<Employee> employees = employeeRepository.findAllByDepartamentId(id);
        model.addAttribute("employees",employees);

        return new ModelAndView("departamentView");
    }

}
