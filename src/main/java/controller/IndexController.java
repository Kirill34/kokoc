package controller;

import model.*;
import org.hibernate.type.LocalDateType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import repo.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {

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

    @GetMapping("/initDB")
    public void initDB()
    {
        SportKind football = new SportKind("Футбол", SportKind.convertMethod.byMinute,"football.png");
        SportKind walk = new SportKind("Ходьба", SportKind.convertMethod.byDistance, "walk.png");
        SportKind bicycle = new SportKind("Велосипед", SportKind.convertMethod.byDistance,"bicycle.png");
        SportKind tennis = new SportKind("Теннис", SportKind.convertMethod.byMinute,"tennis.png");
        sportKindRepository.save(football);
        sportKindRepository.save(walk);
        sportKindRepository.save(bicycle);
        sportKindRepository.save(tennis);

        Departament dep1 = new Departament("Ромашка");
        Departament dep2 = new Departament("Колокольчик");
        departamentRepository.save(dep1);
        departamentRepository.save(dep2);

        Employee emp1 = new Employee("Иванов","Иван",dep1);
        Employee emp2 = new Employee("Петров", "Петр", dep1);
        Employee emp3 = new Employee("Алексеев", "Алексей", dep1);
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);

        Employee emp4 = new Employee("Семенов","Семен", dep2);
        Employee emp5 = new Employee("Денисов","Денис", dep2);
        Employee emp6 = new Employee("Олегов","Олег", dep2);
        employeeRepository.save(emp4);
        employeeRepository.save(emp5);
        employeeRepository.save(emp6);

        SportAction sa1 = new SportAction(emp1,football, LocalDateTime.of(2023,7,15,17,0,0), LocalDateTime.of(2023,7,15,19,0,0),0,0,0);
        SportAction sa2 = new SportAction(emp1,football, LocalDateTime.of(2023,8,15,17,0,0), LocalDateTime.of(2023,8,15,19,0,0),0,0,0);
        SportAction sa3 = new SportAction(emp1,football, LocalDateTime.of(2023,8,19,17,0,0), LocalDateTime.of(2023,8,19,19,0,0),0,0,0);
        SportAction sa4 = new SportAction(emp1,bicycle, LocalDateTime.of(2023,9,29,17,0,0), LocalDateTime.of(2023,9,29,19,0,0), 12.5F,0,0);
        SportAction sa5 = new SportAction(emp1,bicycle, LocalDateTime.of(2023,9,19,17,0,0), LocalDateTime.of(2023,9,19,19,0,0), 22.5F,0,0);
        sportActionRepository.save(sa1);
        sportActionRepository.save(sa2);
        sportActionRepository.save(sa3);
        sportActionRepository.save(sa4);
        sportActionRepository.save(sa5);

        SportAction sa6 = new SportAction(emp2,football,LocalDateTime.of(2023,7,15,17,0,0), LocalDateTime.of(2023,7,15,19,0,0),0,0,0);
        sportActionRepository.save(sa6);

        SportAction sa7 = new SportAction(emp3,football,LocalDateTime.of(2023,7,15,17,0,0), LocalDateTime.of(2023,7,15,19,0,0),0,0,0);
        sportActionRepository.save(sa7);

        CharityEvent ce = new CharityEvent("Помощь амурским тиграм","logo.png",200);
        charityEventRepository.save(ce);


    }
}
