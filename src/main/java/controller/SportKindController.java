package controller;

import io.swagger.models.auth.In;
import model.CharitySportTransaction;
import model.Employee;
import model.SportAction;
import model.SportKind;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sportKind")
public class SportKindController {

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
    public Long add(String name, String logoFile, SportKind.convertMethod method)
    {
        SportKind sportKind = new SportKind(name, method, logoFile);
        sportKindRepository.save(sportKind);
        return sportKind.getId();
    }

    @GetMapping("/all")
    public List<SportKind> getSportKinds()
    {
        return sportKindRepository.findAllByIdGreaterThan(0L);
    }

    @GetMapping("/view")
    public ModelAndView getPageForAllSports(Model model)
    {
        return new ModelAndView("allSportKinds");
    }

    @GetMapping("/{id}/view")
    public ModelAndView getPageForSportKind(Model model, @PathVariable Long id)
    {
        SportKind sportKind = sportKindRepository.findById(id).get();

        model.addAttribute("name",sportKind.getName());
        model.addAttribute("id",sportKind.getId());
        model.addAttribute("logoFile",sportKind.getLogoFile());

        List<SportAction> sportActionList = sportActionRepository.findAllBySportKindId(sportKind.getId());

        List<Employee> employeesInSport = new ArrayList<>();
        int sumMinutes = 0;
        for (SportAction s:
             sportActionList) {
            employeesInSport.add(s.getEmployee());
            sumMinutes+=s.getFinishAction().compareTo(s.getStartAction());
        }
        if (sportKind.getPayMethod()== SportKind.convertMethod.byMinute)
        {
            model.addAttribute("allSum","В сумме этим видом спорта занимались " + sumMinutes + " часов");
        }

        List<CharitySportTransaction> transactions = charitySportTransactionRepository.findAllBySportAction_SportKindId(id);
        int sum = 0;
        for (CharitySportTransaction t: transactions)
        {
            sum+=t.getMoney();
        }
        model.addAttribute("money",sum);

        model.addAttribute("employees",employeesInSport);
        return new ModelAndView("sportKindView");
    }

    @GetMapping("/{id}/byMonthStat")
    public ArrayList<Integer> getCountForEachMonthOfThisYear(@PathVariable Long id)
    {
        SportKind sportKind = sportKindRepository.findById(id).get();
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i<=12; i++)
        {
            res.add(0);
        }

        List<SportAction> sportActionList = sportActionRepository.findAllBySportKindId(sportKind.getId());
        for (SportAction s:
             sportActionList) {
            LocalDateTime dateTime = s.getStartAction();
            Integer monthNum = dateTime.getMonthValue();
            res.set(monthNum, res.get(monthNum)+1); //= res.get(dateTime.getMonthValue()) + 1;
            //res.put(dateTime.getMonth().getValue(), (res.containsKey(dateTime.getMonth().getValue())) ? 1 : (res.get(dateTime.getMonth().getValue()) + 1));
        }
        return res;
    }

}
