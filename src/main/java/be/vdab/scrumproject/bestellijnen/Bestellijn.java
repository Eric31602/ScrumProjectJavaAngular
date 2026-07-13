package be.vdab.scrumproject.bestellijnen;

import be.vdab.scrumproject.artikelen.Artikel;
import be.vdab.scrumproject.bestellingen.Bestelling;
import jakarta.persistence.*;

@Entity
@Table(name = "bestellijnen")
public class Bestellijn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bestellijnId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bestelId")
    private Bestelling bestelling;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artikelId")
    private Artikel artikel;

    private int aantalBesteld;
    private int aantalGeannuleerd;


    public long getBestellijnId() {
        return bestellijnId;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public int getAantalBesteld() {
        return aantalBesteld;
    }

    public int getAantalGeannuleerd() {
        return aantalGeannuleerd;
    }
}
