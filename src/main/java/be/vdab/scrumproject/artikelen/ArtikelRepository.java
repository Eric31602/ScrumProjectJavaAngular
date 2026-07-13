package be.vdab.scrumproject.artikelen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {

    @Query("FROM Artikel a JOIN FETCH a.magazijnplaatsen where a.artikelId = :artikelId")
    public Optional<Artikel> findByArtikelId(long artikelId);

    public Optional<Artikel> findByEan(String ean);
}
