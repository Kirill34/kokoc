package controller;

import model.Employee;
import model.SportAction;
import model.SportKind;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.DepartamentRepository;
import repo.EmployeeRepository;
import repo.SportActionRepository;
import repo.SportKindRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sportAction")
public class SportActionController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    EmployeeRepository employeeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    SportActionRepository sportActionRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private SportKindRepository sportKindRepository;

    @PostMapping("/start")
    public Long add(Long employeeId, Long sportKindId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDateTime)
    {
        Employee employee = employeeRepository.findById(employeeId).get();
        SportKind sportKind = sportKindRepository.findById(sportKindId).get();
        SportAction sportAction = new SportAction(employee,sportKind,startDateTime,null,0,0,0);
        sportActionRepository.save(sportAction);
        return sportAction.getId();
    }

    @PostMapping("/finish")
    public boolean finish(Long sportActionId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime finishDateTime)
    {
        SportAction sportAction = sportActionRepository.findById(sportActionId).get();
        if (sportAction.getSportKind().getPayMethod() == SportKind.convertMethod.byMinute) {
            sportAction.setFinishAction(finishDateTime);
            sportActionRepository.save(sportAction);
            return true;
        }
        return false;
    }

    @PostMapping("/finishDistance")
    public boolean finishByDistanceAction(Long sportActionId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime finishDateTime, float distance)
    {
        SportAction sportAction = sportActionRepository.findById(sportActionId).get();
        if (sportAction.getSportKind().getPayMethod() == SportKind.convertMethod.byDistance)
        {
            sportAction.setDistance(distance);
            sportAction.setFinishAction(finishDateTime);


            int money = (int)(distance*10);
            sportAction.setMoney(money);
            sportActionRepository.save(sportAction);

            return true;
        }
        return false;
    }



    @GetMapping("/forEmployee")
    public List<SportAction> getSportActionsByEmployee(Long employeeId)
    {
        return sportActionRepository.findAllByEmployeeId(employeeId);
    }
}
