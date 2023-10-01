package controller;

import model.CharitySportTransaction;
import model.Employee;
import model.SportAction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private CharitySportTransactionRepository charitySportTransactionRepository;

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

    @GetMapping("/{id}/my/view")
    public ModelAndView getMyView(Model model, @PathVariable Long id)
    {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("firstName",employee.getFirstName());
        model.addAttribute("secondName",employee.getSecondName());
        model.addAttribute("id",employee.getId());
        List<CharitySportTransaction> transactions = charitySportTransactionRepository.findAllBySportActionEmployeeId(id);
        int sum = 0;
        for (CharitySportTransaction t: transactions)
        {
            sum+=t.getMoney();
        }
        model.addAttribute("money",sum);
        return new ModelAndView("myPage");
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

    @GetMapping("/{id}/stats/byMonth")
    public List<Integer> getByMonthStat(@PathVariable Long id)
    {
        Employee employee = employeeRepository.findById(id).get();
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i<=12; i++)
        {
            res.add(0);
        }

        List<SportAction> sportActionList = sportActionRepository.findAllByEmployee(employee);
        for (SportAction s:
                sportActionList) {
            LocalDateTime dateTime = s.getStartAction();
            Integer monthNum = dateTime.getMonthValue();
            res.set(monthNum, res.get(monthNum)+1);// add(monthNum,1); //= res.get(dateTime.getMonthValue()) + 1;
            //res.put(dateTime.getMonth().getValue(), (res.containsKey(dateTime.getMonth().getValue())) ? 1 : (res.get(dateTime.getMonth().getValue()) + 1));
        }
        return res;
    }
}
