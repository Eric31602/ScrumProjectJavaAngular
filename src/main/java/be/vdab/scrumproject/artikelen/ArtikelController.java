package be.vdab.scrumproject.artikelen;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artikels")
public class ArtikelController {
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

//    @GetMapping("{artikelId}")
//    public ArtikelDetails findByArtikelId(@PathVariable long artikelId) {
//        return artikelService.findByArtikelId(artikelId)
//                .map(artikel -> new ArtikelDetails(artikel))
//                .orElseThrow(ArtikelNietGevondenException::new);
//    }

    @GetMapping("{artikelId}")
    public ResponseEntity<ArtikelDetails> findByArtikelId(@PathVariable long artikelId) {
        return artikelService.findByArtikelId(artikelId)
                .map(artikel -> ResponseEntity.ok(new ArtikelDetails(artikel)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
