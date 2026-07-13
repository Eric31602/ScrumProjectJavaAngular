package be.vdab.scrumproject.inkomendeleveringen;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("leveringen")
public class InkomendeLeveringController {
    private final InkomendeLeveringService inkomendeLeveringService;

    public InkomendeLeveringController(InkomendeLeveringService inkomendeLeveringService) {
        this.inkomendeLeveringService = inkomendeLeveringService;
    }

    @PostMapping("inkomend")
    public long registreerOntvangst(@RequestBody @Valid InkomendeLeveringInfo inkomendeLeveringInfo) {
        return inkomendeLeveringService.registreerOntvangst(inkomendeLeveringInfo);
    }

}
