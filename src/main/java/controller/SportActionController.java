package controller;

import model.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import repo.*;

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

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private CharityEventRepository charityEventRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private CharitySportTransactionRepository charitySportTransactionRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private ActionGeoPointRepository actionGeoPointRepository;

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
    public boolean finish(Long sportActionId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime finishDateTime,Long eventId)
    {
        CharityEvent charityEvent = charityEventRepository.findById(eventId).get();
        SportAction sportAction = sportActionRepository.findById(sportActionId).get();
        if (sportAction.getSportKind().getPayMethod() == SportKind.convertMethod.byMinute) {
            sportAction.setFinishAction(finishDateTime);
            sportActionRepository.save(sportAction);

            int money = 10;
            sportAction.setMoney(money);
            sportActionRepository.save(sportAction);

            CharitySportTransaction charitySportTransaction = new CharitySportTransaction();
            charitySportTransaction.setSportAction(sportAction);
            charitySportTransaction.setCharityEvent(charityEvent);
            charitySportTransaction.setMoney(money);
            charitySportTransactionRepository.save(charitySportTransaction);

            return true;
        }
        return false;
    }

    @PostMapping("/finishDistance")
    public boolean finishByDistanceAction(Long sportActionId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime finishDateTime, float distance,Long eventId)
    {
        CharityEvent charityEvent = charityEventRepository.findById(eventId).get();
        SportAction sportAction = sportActionRepository.findById(sportActionId).get();
        if (sportAction.getSportKind().getPayMethod() == SportKind.convertMethod.byDistance)
        {
            sportAction.setDistance(distance);
            sportAction.setFinishAction(finishDateTime);


            int money = (int)(distance*10);
            sportAction.setMoney(money);
            sportActionRepository.save(sportAction);

            CharitySportTransaction charitySportTransaction = new CharitySportTransaction();
            charitySportTransaction.setSportAction(sportAction);
            charitySportTransaction.setCharityEvent(charityEvent);
            charitySportTransaction.setMoney(money);
            charitySportTransactionRepository.save(charitySportTransaction);

            return true;
        }
        return false;
    }

    @PostMapping("/{id}/addActionGeoPoint")
    public void addActionGeoPoint(@PathVariable Long id, double latitude, double longitude, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTime)
    {
        //ActionGeoPoint actionGeoPoint = actionGeoPointRepository.findById(id).get();
        SportAction sportAction = sportActionRepository.findById(id).get();
        ActionGeoPoint actionGeoPoint = new ActionGeoPoint(latitude, longitude, sportAction, dateTime);
        actionGeoPointRepository.save(actionGeoPoint);
    }



    @GetMapping("/forEmployee")
    public List<SportAction> getSportActionsByEmployee(Long employeeId)
    {
        return sportActionRepository.findAllByEmployeeId(employeeId);
    }
}
