package controller;

import model.SportKind;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.SportKindRepository;

import java.util.List;

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
        model.addAttribute("logoFile",sportKind.getLogoFile());

        return new ModelAndView("sportKindView");

    }
}
