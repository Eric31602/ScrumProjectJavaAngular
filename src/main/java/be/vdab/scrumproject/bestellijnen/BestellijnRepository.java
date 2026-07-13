package be.vdab.scrumproject.bestellijnen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BestellijnRepository extends JpaRepository<Bestellijn, Long> {

    @Query("""
from Bestellijn b join fetch b.artikel
where b.bestelling.bestelId = :bestelId
""")
    public List<Bestellijn> findBestellijnByBestelId(long bestelId);
}
