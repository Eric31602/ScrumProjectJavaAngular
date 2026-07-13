package be.vdab.scrumproject.magazijnplaatsen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MagazijnplaatsRepository extends JpaRepository<Magazijnplaats, Long> {

    @Query("""
from Magazijnplaats m join fetch m.artikel
where m.artikel.artikelId = :artikelId
""")
    public List<Magazijnplaats> findByArtikelId(long artikelId);

    @Query("""
from Magazijnplaats m
where m.artikel is null
order by m.magazijnPlaatsId
""")
    public List<Magazijnplaats> findVrijePlaatsen();


    @Modifying
    @Query("""
update Magazijnplaats m
set m.artikel.artikelId = :artikelId,
m.aantal = :aantal
where m.magazijnPlaatsId = :magazijnPlaatsId
""")
    public void setLegePlaats(long artikelId, int aantal, long magazijnPlaatsId);
}
