package controller;

import model.SportKind;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.SportKindRepository;

@RestController
@RequestMapping("/sportKind")
public class SportKindController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private SportKindRepository sportKindRepository;

    @PostMapping("/add")
    public Long add(String name, String logoFile, SportKind.convertMethod method)
    {
        SportKind sportKind = new SportKind(name, method, logoFile);
        sportKindRepository.save(sportKind);
        return sportKind.getId();
    }
}
