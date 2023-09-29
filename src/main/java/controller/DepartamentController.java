package controller;

import model.Departament;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.DepartamentRepository;

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
}
