package be.vdab.scrumproject.inkomendeleveringslijnen;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InkomendeLeveringsLijnId implements Serializable {

    private long inkomendeLeveringsId;
    private long artikelId;
    private long magazijnPlaatsId;


}
