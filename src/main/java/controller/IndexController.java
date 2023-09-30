package controller;

import model.Departament;
import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import repo.DepartamentRepository;
import repo.EmployeeRepository;
import org.springframework.ui.Model;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("index");//"Hello";
    }

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello";
    }

    @GetMapping("/login")
    public ModelAndView login(Model model)
    {
        List<Departament> departaments = departamentRepository.findAllByIdGreaterThan(0L);
        model.addAttribute("departaments",departaments);
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model)
    {
        List<Departament> departaments = departamentRepository.findAllByIdGreaterThan(0L);
        model.addAttribute("departaments",departaments);
        return new ModelAndView("register");
    }
}
