package be.vdab.scrumproject.inkomendeleveringslijnen;

import be.vdab.scrumproject.artikelen.Artikel;
import be.vdab.scrumproject.inkomendeleveringen.InkomendeLevering;
import be.vdab.scrumproject.magazijnplaatsen.Magazijnplaats;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "inkomendeleveringslijnen")
public class InkomendeLeveringsLijn {

    @EmbeddedId
    private InkomendeLeveringsLijnId id;

    @ManyToOne
    @MapsId("inkomendeLeveringsId")
    @JoinColumn(name = "inkomendeLeveringsId")
    private InkomendeLevering inkomendeLevering;

    @ManyToOne
    @MapsId("artikelId")
    @JoinColumn(name = "artikelId")
    private Artikel artikel;

    private int aantalBesteld;
    private int aantalGoedgekeurd;
    private int aantalTeruggestuurd;

    @ManyToOne
    @MapsId("magazijnPlaatsId")
    @JoinColumn(name = "magazijnPlaatsId")
    private Magazijnplaats magazijnplaats;

    public InkomendeLeveringsLijn(InkomendeLevering inkomendeLevering, Artikel artikel, int aantalBesteld, int aantalGoedgekeurd,
                                  int aantalTeruggestuurd, Magazijnplaats magazijnplaats) {
        this.inkomendeLevering = inkomendeLevering;
        this.artikel = artikel;
        this.aantalBesteld = aantalBesteld;
        this.aantalGoedgekeurd = aantalGoedgekeurd;
        this.aantalTeruggestuurd = aantalTeruggestuurd;
        this.magazijnplaats = magazijnplaats;

        this.id = new InkomendeLeveringsLijnId(
                inkomendeLevering.getInkomendeLeveringsId(),
                artikel.getArtikelId(),
                magazijnplaats.getMagazijnPlaatsId());
    }
}
