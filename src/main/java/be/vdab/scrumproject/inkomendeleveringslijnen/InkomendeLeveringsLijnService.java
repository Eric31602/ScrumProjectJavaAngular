package be.vdab.scrumproject.inkomendeleveringslijnen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InkomendeLeveringsLijnService {
    private final InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository;

    public InkomendeLeveringsLijnService(InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository) {
        this.inkomendeLeveringsLijnRepository = inkomendeLeveringsLijnRepository;
    }

    public List<LeveringsLijnOverzicht> findLeveringsLijnen(long id) {
        return inkomendeLeveringsLijnRepository.findLeveringsLijnen(id);
    }
}
