package be.vdab.scrumproject.inkomendeleveringen;

import be.vdab.scrumproject.artikelen.ArtikelNietGevondenException;
import be.vdab.scrumproject.artikelen.ArtikelRepository;
import be.vdab.scrumproject.inkomendeleveringslijnen.InkomendeLeveringsLijn;
import be.vdab.scrumproject.inkomendeleveringslijnen.InkomendeLeveringsLijnRepository;
import be.vdab.scrumproject.leveranciers.LeverancierNietgevonden;
import be.vdab.scrumproject.leveranciers.LeverancierRepository;
import be.vdab.scrumproject.magazijnplaatsen.MagazijnplaatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class InkomendeLeveringService {
    private final InkomendeLeveringRepository inkomendeLeveringRepository;
    private final ArtikelRepository artikelRepository;
    private final MagazijnplaatsRepository magazijnplaatsRepository;
    private final LeverancierRepository leverancierRepository;
    private final InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository;

    @Transactional
    public long registreerOntvangst(InkomendeLeveringInfo inkomendeLeveringInfo) {
        var leverancier = leverancierRepository.findByNaam(inkomendeLeveringInfo.leverancierNaam())
                .orElseThrow(LeverancierNietgevonden::new);

//        var levering = new InkomendeLevering(leverancier, inkomendeLeveringInfo.leveringsbonNummer(),
//                inkomendeLeveringInfo.leveringsbondatum(), LocalDate.now(), 4);

        var levering = InkomendeLevering.builder().leverancier(leverancier)
                .leveringsbonNummer(inkomendeLeveringInfo.leveringsbonNummer())
                .leveringsbondatum(inkomendeLeveringInfo.leveringsbondatum())
                .leverDatum(LocalDate.now())
                .ontvangerPersoneelslidId(4).build();
        inkomendeLeveringRepository.save(levering);

        var lijnen = inkomendeLeveringInfo.nieuweLeveringsLijnen();

        for (var lijn : lijnen) {
            var ean = lijn.ean();
            var artikel = artikelRepository.findByEan(ean).orElseThrow(ArtikelNietGevondenException::new);
            var plaatsen = magazijnplaatsRepository.findByArtikelId(artikel.getArtikelId());
            var aantalTeVerwerken = lijn.aantalGoedgekeurd();

            for (var plaats : plaatsen) {
                if (aantalTeVerwerken == 0) {
                    break;
                }

                var capaciteit = artikel.getMaxAantalInMagazijnPlaats();
                var vrijeRuimte = capaciteit - plaats.getAantal();

                if (vrijeRuimte > 0) {
                    var toeTeVoegen = Math.min(vrijeRuimte, aantalTeVerwerken);
                    plaats.setAantal(plaats.getAantal() + toeTeVoegen);
                    aantalTeVerwerken -= toeTeVoegen;

                    var nieuweLijn = new InkomendeLeveringsLijn(levering, artikel, lijn.aantalBesteld(),
                            toeTeVoegen, lijn.aantalTeruggestuurd(), plaats);
                    inkomendeLeveringsLijnRepository.save(nieuweLijn);
                }
            }

            if (aantalTeVerwerken > 0) {
                var vrijePlaatsen = magazijnplaatsRepository.findVrijePlaatsen();

                for (var vrijePlaats : vrijePlaatsen) {
                    if (aantalTeVerwerken == 0) {
                        break;
                    }

                    var capaciteit = artikel.getMaxAantalInMagazijnPlaats();
                    var vrijeRuimte = capaciteit - vrijePlaats.getAantal();

                    if (vrijeRuimte > 0) {
                        var toeTeVoegen = Math.min(vrijeRuimte, aantalTeVerwerken);
                        magazijnplaatsRepository.setLegePlaats(artikel.getArtikelId(), toeTeVoegen, vrijePlaats.getMagazijnPlaatsId());
                        aantalTeVerwerken -= toeTeVoegen;

                        var nieuweLijn = new InkomendeLeveringsLijn(levering, artikel, lijn.aantalBesteld(),
                                toeTeVoegen, lijn.aantalTeruggestuurd(), vrijePlaats);
                        inkomendeLeveringsLijnRepository.save(nieuweLijn);
                    }
                }
            }

            artikel.setVoorraad(artikel.getVoorraad() + lijn.aantalGoedgekeurd());
        }
        return levering.getInkomendeLeveringsId();
    }
}
