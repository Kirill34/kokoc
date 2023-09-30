package controller;

import model.CharityEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.CharityEventRepository;

import java.util.List;

@RestController
@RequestMapping("/chairEvent")
public class ChairEventController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    CharityEventRepository charityEventRepository;

    @PostMapping("/add")
    public Long add(String name, int cost)
    {
        CharityEvent charityEvent = new CharityEvent(name,"default.png",cost);
        charityEventRepository.save(charityEvent);
        return charityEvent.getId();
    }

    @GetMapping("/all")
    public List<CharityEvent> getAll()
    {
        return charityEventRepository.findAllByIdGreaterThan(0L);
    }

}
