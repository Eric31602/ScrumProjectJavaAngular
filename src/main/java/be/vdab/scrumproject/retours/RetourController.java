package be.vdab.scrumproject.retours;

import be.vdab.scrumproject.bestellijnen.ArtikelDetailsGesorteerdOpLocatie;
import be.vdab.scrumproject.bestellijnen.BestellijnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("retours")
public class RetourController {
    private final BestellijnService bestellijnService;
    private final RetourService retourService;

    public RetourController(BestellijnService bestellijnService, RetourService retourService) {
        this.bestellijnService = bestellijnService;
        this.retourService = retourService;
    }

    @GetMapping("{id}")
    public List<ArtikelDetailsGesorteerdOpLocatie> findRetoursByBestelId(@PathVariable long id) {
        return bestellijnService.findBestellijnByBestelId(id);
    }

    @PutMapping("start/{id}")
    public void beginRetour(@PathVariable long id) {
        retourService.beginRetour(id);
    }

    @PutMapping("beschadigd/{id}")
    public void beschadigd(@PathVariable long id) {
        retourService.retourBeschadigd(id);
    }

    @PutMapping("afwerken/{id}")
    public void bevestigRetour(@PathVariable long id) {
        retourService.bevestigRetour(id);
    }
}
