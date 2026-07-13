package be.vdab.scrumproject.magazijnplaatsen;

import be.vdab.scrumproject.artikelen.Artikel;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "magazijnplaatsen")
@Setter
public class Magazijnplaats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long magazijnPlaatsId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artikelId")
    private Artikel artikel;

    private String rij;
    private int rek;
    private int aantal;

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public String getRij() {
        return rij;
    }

    public int getRek() {
        return rek;
    }

    public int getAantal() {
        return aantal;
    }


}
