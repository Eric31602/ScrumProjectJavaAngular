package be.vdab.scrumproject.retours;

import be.vdab.scrumproject.artikelen.ArtikelNietGevondenException;
import be.vdab.scrumproject.artikelen.ArtikelRepository;
import be.vdab.scrumproject.bestellijnen.BestellijnRepository;
import be.vdab.scrumproject.bestellingen.BestellingNietGevondenException;
import be.vdab.scrumproject.bestellingen.BestellingRepository;
import be.vdab.scrumproject.magazijnplaatsen.MagazijnplaatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RetourService {
    private final BestellingRepository bestellingRepository;
    private final BestellijnRepository bestellijnRepository;
    private final ArtikelRepository artikelRepository;
    private final MagazijnplaatsRepository magazijnplaatsRepository;

    public RetourService(BestellingRepository bestellingRepository, BestellijnRepository bestellijnRepository, ArtikelRepository artikelRepository, MagazijnplaatsRepository magazijnplaatsRepository) {
        this.bestellingRepository = bestellingRepository;
        this.bestellijnRepository = bestellijnRepository;
        this.artikelRepository = artikelRepository;
        this.magazijnplaatsRepository = magazijnplaatsRepository;
    }

    @Transactional
    public void beginRetour(long bestelId) {
        var bestelling = bestellingRepository.findById(bestelId).orElseThrow(BestellingNietGevondenException::new);
        bestelling.setBestellingsStatusId(9);
    }

    @Transactional
    public void retourBeschadigd(long bestelId) {
        var bestelling = bestellingRepository.findById(bestelId).orElseThrow(BestellingNietGevondenException::new);
        bestelling.setBestellingsStatusId(8);
    }

    @Transactional
    public void bevestigRetour(long bestelId) {
        var bestelling = bestellingRepository.findById(bestelId)
                .orElseThrow(() -> new BestellingNietGevondenException());
        var lijnen = bestellijnRepository.findBestellijnByBestelId(bestelId);
        for (var lijn : lijnen) {
            var artikel = artikelRepository.findByArtikelId(lijn.getArtikel().getArtikelId())
                    .orElseThrow(() -> new ArtikelNietGevondenException());

            int teVerdelen = lijn.getAantalBesteld();

            var plaatsen = magazijnplaatsRepository.findByArtikelId(artikel.getArtikelId());
            for (var plaats : plaatsen) {
                if (teVerdelen <= 0) break;

                int vrijeRuimte = artikel.getMaxAantalInMagazijnPlaats() - plaats.getAantal();
                if (vrijeRuimte > 0) {
                    int toeTeVoegen = Math.min(teVerdelen, vrijeRuimte);
                    plaats.setAantal(plaats.getAantal() + toeTeVoegen);
                    teVerdelen -= toeTeVoegen;
                }
            }

            if (teVerdelen > 0) {
                var vrijePlaaten = magazijnplaatsRepository.findVrijePlaatsen();
                for (var vrijePlaats : vrijePlaaten) {
                    int toeTeVoegen = Math.min(teVerdelen, artikel.getMaxAantalInMagazijnPlaats());
                    vrijePlaats.setArtikel(artikel);
                    vrijePlaats.setAantal(toeTeVoegen);
                    teVerdelen -= toeTeVoegen;
                }
            }

            artikel.setVoorraad(artikel.getVoorraad() + lijn.getAantalBesteld());
            artikelRepository.save(artikel);
        }
        bestelling.setBestellingsStatusId(9);
    }
}
