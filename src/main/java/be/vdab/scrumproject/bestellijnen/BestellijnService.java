package be.vdab.scrumproject.bestellijnen;

import be.vdab.scrumproject.artikelen.ArtikelNietGevondenException;
import be.vdab.scrumproject.artikelen.ArtikelRepository;
import be.vdab.scrumproject.magazijnplaatsen.MagazijnplaatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BestellijnService {
    private final BestellijnRepository bestellijnRepository;
    private final ArtikelRepository artikelRepository;
    private final MagazijnplaatsRepository magazijnplaatsRepository;

    public BestellijnService(BestellijnRepository bestellijnRepository, ArtikelRepository artikelRepository,
                             MagazijnplaatsRepository magazijnplaatsRepository) {
        this.bestellijnRepository = bestellijnRepository;
        this.artikelRepository = artikelRepository;
        this.magazijnplaatsRepository = magazijnplaatsRepository;
    }

    public List<ArtikelDetailsGesorteerdOpLocatie> findBestellijnByBestelId(long bestelId) {
        var lijnen = bestellijnRepository.findBestellijnByBestelId(bestelId);
        var logischeVolgorde = new ArrayList<ArtikelDetailsGesorteerdOpLocatie>();

        for (var lijn : lijnen) {
            int aantalBesteld = lijn.getAantalBesteld();
            var artikel = artikelRepository.findByArtikelId(lijn.getArtikel().getArtikelId())
                    .orElseThrow(() -> new ArtikelNietGevondenException());

            var plaatsen = magazijnplaatsRepository.findByArtikelId(artikel.getArtikelId());
            for (var plaats : plaatsen) {

                if (aantalBesteld <= 0) {
                    break;
                }

                var maxAantal = artikel.getMaxAantalInMagazijnPlaats();
                var aantalTePakken = Math.min(maxAantal, aantalBesteld);

                logischeVolgorde.add(new ArtikelDetailsGesorteerdOpLocatie(plaats.getRij(),
                        plaats.getRek(), artikel.getNaam(), aantalTePakken, artikel.getArtikelId()));

                aantalBesteld -= aantalTePakken;
            }
        }

        return logischeVolgorde.stream().sorted(Comparator.comparing(ArtikelDetailsGesorteerdOpLocatie::rij)
                .thenComparing(ArtikelDetailsGesorteerdOpLocatie::rek)).toList();
    }
}
