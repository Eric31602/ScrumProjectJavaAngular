package be.vdab.scrumproject.bestellijnen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bestellijnen")
public class BestellijnController {
    private final BestellijnService bestellijnService;

    public BestellijnController(BestellijnService bestellijnService) {
        this.bestellijnService = bestellijnService;
    }

    @GetMapping("{bestelId}")
    public List<ArtikelDetailsGesorteerdOpLocatie> findBestellijnByBestelId(@PathVariable long bestelId) {
        return bestellijnService.findBestellijnByBestelId(bestelId);
    }
}
