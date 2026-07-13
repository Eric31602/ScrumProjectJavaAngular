package be.vdab.scrumproject.artikelen;

import be.vdab.scrumproject.bestellijnen.Bestellijn;
import be.vdab.scrumproject.leveranciers.Leverancier;
import be.vdab.scrumproject.magazijnplaatsen.Magazijnplaats;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artikelen")
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long artikelId;
    private String ean;
    private String naam;
    private String beschrijving;
    private BigDecimal prijs;
    private int gewichtInGram;
    private int bestelPeil;
    private int voorraad;
    private int minimumVoorraad;
    private int maximumVoorraad;
    private int levertijd;
    private int aantalBesteldLeverancier;
    private int maxAantalInMagazijnPlaats;

    @ManyToOne(optional = false)
    @JoinColumn(name = "leveranciersId")
    private Leverancier leverancier;

    @OneToMany(mappedBy = "artikel")
    @OrderBy("rij, rek")
    private Set<Magazijnplaats> magazijnplaatsen = new LinkedHashSet<>();

    @OneToMany(mappedBy = "artikel")
    private Set<Bestellijn> bestellijnen = new LinkedHashSet<>();


    public int getGewichtInGram() {
        return gewichtInGram;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getEan() {
        return ean;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getBestelPeil() {
        return bestelPeil;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getMinimumVoorraad() {
        return minimumVoorraad;
    }

    public int getMaximumVoorraad() {
        return maximumVoorraad;
    }

    public int getLevertijd() {
        return levertijd;
    }

    public int getAantalBesteldLeverancier() {
        return aantalBesteldLeverancier;
    }

    public int getMaxAantalInMagazijnPlaats() {
        return maxAantalInMagazijnPlaats;
    }

    public Leverancier getLeverancier() {
        return leverancier;
    }

    public Set<Magazijnplaats> getMagazijnplaatsen() {
        return Collections.unmodifiableSet(magazijnplaatsen);
    }

    public Set<Bestellijn> getBestellijnen() {
        return Collections.unmodifiableSet(bestellijnen);
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
}


