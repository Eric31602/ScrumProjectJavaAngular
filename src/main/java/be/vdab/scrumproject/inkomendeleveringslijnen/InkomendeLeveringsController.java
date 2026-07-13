package be.vdab.scrumproject.inkomendeleveringslijnen;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("leveringen")
public class InkomendeLeveringsController {
    private final InkomendeLeveringsLijnService inkomendeLeveringsLijnService;

    public InkomendeLeveringsController(InkomendeLeveringsLijnService inkomendeLeveringsLijnService) {
        this.inkomendeLeveringsLijnService = inkomendeLeveringsLijnService;
    }

    @GetMapping("plaatsing/{id}")
    public List<LeveringsLijnOverzicht> findLeveringsLijnen(@PathVariable long id) {
        return inkomendeLeveringsLijnService.findLeveringsLijnen(id);
    }
}
