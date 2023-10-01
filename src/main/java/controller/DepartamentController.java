package controller;

import model.CharitySportTransaction;
import model.Departament;
import model.Employee;
import model.SportAction;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/departament")
public class DepartamentController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    EmployeeRepository employeeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    SportActionRepository sportActionRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private SportKindRepository sportKindRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private CharitySportTransactionRepository charitySportTransactionRepository;

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

        List<SportAction> sportActionList = sportActionRepository.findAllByEmployeeDepartamentId(id);

        HashMap<String,Integer> sportTimes = new HashMap<>();
        for (SportAction s: sportActionList)
        {
            String sportName = s.getSportKind().getName();
            if (!sportTimes.containsKey(sportName))
                sportTimes.put(sportName,0);
            int times = sportTimes.get(sportName);
            sportTimes.put(sportName,times+1);
        }

        List<CharitySportTransaction> transactions = charitySportTransactionRepository.findAllBySportActionEmployeeDepartamentId(id);
        int sum = 0;
        for (CharitySportTransaction t: transactions)
        {
            sum+=t.getMoney();
        }
        model.addAttribute("money",sum);

        model.addAttribute("sportTimes",sportTimes);

        return new ModelAndView("departamentView");
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable Long id)
    {
        return employeeRepository.findAllByDepartamentId(id);
    }
}
