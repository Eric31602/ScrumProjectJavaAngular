package be.vdab.scrumproject.bestellingen;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BestellingRepository extends JpaRepository<Bestelling, Long> {

@Query("""
select new be.vdab.scrumproject.bestellingen.BestellingInfo( 
        b.bestelId, b.besteldatum, SUM(bl.aantalBesteld),
        ROUND(SUM(bl.aantalBesteld * a.gewichtInGram / 1000.0), 2)
        )
from Bestelling b join b.bestellijnen bl join bl.artikel a
where b.bestellingsStatusId = 2
and bl.aantalBesteld < a.voorraad
group by b.bestelId, b.besteldatum
order by b.bestelId, b.besteldatum
""")
    public List<BestellingInfo> findAllBestellingInfo();

    public long countByBestellingsStatusId(long bestellingsStatusId);

    @Query("""
select new be.vdab.scrumproject.bestellingen.BestellingInfo( 
        b.bestelId, b.besteldatum, SUM(bl.aantalBesteld),
        ROUND(SUM(bl.aantalBesteld * a.gewichtInGram / 1000.0), 2)
        )
from Bestelling b join b.bestellijnen bl join bl.artikel a
where b.bestellingsStatusId = 2
and bl.aantalBesteld < a.voorraad
group by b.bestelId, b.besteldatum
order by b.bestelId, b.besteldatum
""")
    public List<BestellingInfo> findEersteVijf(Pageable pageable);
}
