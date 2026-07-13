package be.vdab.scrumproject.bestellingen;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bestellingen")
public class BestellingController {
    private final BestellingService bestellingService;

    public BestellingController(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    @GetMapping("volgendeId")
    public long findVolgendeId() {
        return bestellingService.findVolgendeId()
                .orElseThrow(BestellingNietGevondenException::new);
    }

    @PostMapping("afwerken/{bestelId}")
    public long werkBestellingAf(@PathVariable long bestelId) {
        return bestellingService.werkBestellingAf(bestelId);
    }

    @GetMapping("totaal")
    public long findTotaal() {
        return bestellingService.findTotaal();
    }

    @GetMapping("eersteVijf")
    public List<BestellingInfo> findEersteVijf() {
        return bestellingService.findEersteVijf();
    }
}
