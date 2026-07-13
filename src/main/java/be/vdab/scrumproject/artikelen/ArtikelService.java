package be.vdab.scrumproject.artikelen;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ArtikelService {
    private final ArtikelRepository artikelRepository;



    public Optional<Artikel> findByArtikelId(long artikelId) {
        return artikelRepository.findByArtikelId(artikelId);
    }
}
