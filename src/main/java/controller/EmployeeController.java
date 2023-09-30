package controller;

import model.Employee;
import model.SportAction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.DepartamentRepository;
import repo.EmployeeRepository;
import repo.SportActionRepository;
import repo.SportKindRepository;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    EmployeeRepository employeeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    SportActionRepository sportActionRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private SportKindRepository sportKindRepository;

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

    @GetMapping("/{id}/stats/sportTimes")
    public HashMap<String,Integer> getSportTimes(@PathVariable Long id)
    {
        HashMap<String,Integer> sportTimes = new HashMap<>();
        List<SportAction> sportActionList = sportActionRepository.findAllByEmployeeId(id);
        for (SportAction s: sportActionList)
        {
            String sportName = s.getSportKind().getName();
            if (!sportTimes.containsKey(sportName))
                sportTimes.put(sportName,0);
            int times = sportTimes.get(sportName);
            sportTimes.put(sportName,times+1);
        }
        return sportTimes;
    }
}
