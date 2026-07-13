package be.vdab.scrumproject.bestellingen;

import be.vdab.scrumproject.artikelen.ArtikelNietGevondenException;
import be.vdab.scrumproject.artikelen.ArtikelRepository;
import be.vdab.scrumproject.bestellijnen.BestellijnRepository;
import be.vdab.scrumproject.uitgaandeleveringen.UitgaandeLevering;
import be.vdab.scrumproject.uitgaandeleveringen.UitgaandeLeveringRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BestellingService {
    private final BestellingRepository bestellingRepository;
    private final ArtikelRepository artikelRepository;
    private final BestellijnRepository bestellijnRepository;
    private final UitgaandeLeveringRepository uitgaandeLeveringRepository;

    public BestellingService(BestellingRepository bestellingRepository, ArtikelRepository artikelRepository,
                             BestellijnRepository bestellijnRepository, UitgaandeLeveringRepository uitgaandeLeveringRepository) {
        this.bestellingRepository = bestellingRepository;
        this.artikelRepository = artikelRepository;
        this.bestellijnRepository = bestellijnRepository;
        this.uitgaandeLeveringRepository = uitgaandeLeveringRepository;
    }

    public Optional<Long> findVolgendeId() {
        return bestellingRepository.findAllBestellingInfo().stream()
                .map(BestellingInfo::bestelId).findFirst();
    }

    @Transactional
    public long werkBestellingAf(long bestelId) {
        var lijnen = bestellijnRepository.findBestellijnByBestelId(bestelId);
        for (var lijn : lijnen) {
            var artikel = artikelRepository.findById(lijn.getArtikel().getArtikelId())
                    .orElseThrow(ArtikelNietGevondenException::new);
            artikel.setVoorraad(artikel.getVoorraad() - lijn.getAantalBesteld());
        }
        var klantId = bestellingRepository.findById(bestelId)
                .orElseThrow(BestellingNietGevondenException::new)
                .getKlantId();
        var bestelling = bestellingRepository.findById(bestelId)
                .orElseThrow(BestellingNietGevondenException::new);

        var uitgaandeLevering = new UitgaandeLevering(bestelling, LocalDate.now(), null, "",
               klantId, 1);
        uitgaandeLeveringRepository.save(uitgaandeLevering);
        return uitgaandeLevering.getUitgaandeLeveringsId();
    }

    public long findTotaal() {
        return bestellingRepository.countByBestellingsStatusId(2);
    }

    public List<BestellingInfo> findEersteVijf() {
        return bestellingRepository.findEersteVijf(PageRequest.of(0, 5));
    }
}
