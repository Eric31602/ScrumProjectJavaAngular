package be.vdab.scrumproject.leveranciers;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LeverancierService {
    private final LeverancierRepository leverancierRepository;

    public LeverancierService(LeverancierRepository leverancierRepository) {
        this.leverancierRepository = leverancierRepository;
    }

    public List<Leverancier> findAll() {
        return leverancierRepository.findAllByOrderByNaam();
    }

}
