package controller;

import model.Departament;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import repo.DepartamentRepository;

import java.util.List;

@RestController
@RequestMapping("/departament")
public class DepartamentController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    DepartamentRepository departamentRepository;

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
}
