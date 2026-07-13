package be.vdab.scrumproject.inkomendeleveringslijnen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InkomendeLeveringsLijnRepository extends JpaRepository<InkomendeLeveringsLijn, InkomendeLeveringsLijnId> {

    @Query("""
select new be.vdab.scrumproject.inkomendeleveringslijnen.LeveringsLijnOverzicht(
CONCAT(m.rij, m.rek) as locatie, a.naam, i.aantalGoedgekeurd)
from InkomendeLeveringsLijn i join i.artikel a join i.magazijnplaats m
where i.inkomendeLevering.inkomendeLeveringsId = :id
""")
    public List<LeveringsLijnOverzicht> findLeveringsLijnen(long id);
}


