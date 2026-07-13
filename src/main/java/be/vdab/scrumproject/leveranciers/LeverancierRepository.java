package be.vdab.scrumproject.leveranciers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeverancierRepository extends JpaRepository<Leverancier, Long> {

    public List<Leverancier> findAllByOrderByNaam();

    public Optional<Leverancier> findByNaam(String naam);
}
