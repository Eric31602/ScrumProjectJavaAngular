package be.vdab.scrumproject.bestellingen;

import be.vdab.scrumproject.bestellijnen.Bestellijn;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bestellingen")
public class Bestelling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private long bestelId;
    private LocalDateTime besteldatum;
    private long klantId;
    private int betaald;
    private String betalingscode;
    private int betaalwijzeId;
    private int annulatie;
    private LocalDate annulatiedatum;
    private String terugbetalingscode;
    private long bestellingsStatusId;
    private int actiecodeGebruikt;
    private String bedrijfsnaam;
    private String btwNummer;
    private String voornaam;
    private String familienaam;
    private long facturatieAdresId;
    private long leveringsAdresId;

    @OneToMany(mappedBy = "bestelling")
    private Set<Bestellijn> bestellijnen = new LinkedHashSet<>();

    public long getBestelId() {
        return bestelId;
    }

    public LocalDateTime getBesteldatum() {
        return besteldatum;
    }

    public long getKlantId() {
        return klantId;
    }

    public int getBetaald() {
        return betaald;
    }

    public String getBetalingscode() {
        return betalingscode;
    }

    public int getBetaalwijzeId() {
        return betaalwijzeId;
    }

    public int getAnnulatie() {
        return annulatie;
    }

    public LocalDate getAnnulatiedatum() {
        return annulatiedatum;
    }

    public String getTerugbetalingscode() {
        return terugbetalingscode;
    }

    public long getBestellingsStatusId() {
        return bestellingsStatusId;
    }

    public int getActiecodeGebruikt() {
        return actiecodeGebruikt;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public long getFacturatieAdresId() {
        return facturatieAdresId;
    }

    public long getLeveringsAdresId() {
        return leveringsAdresId;
    }

    public void setBestellingsStatusId(long bestellingsStatusId) {
        this.bestellingsStatusId = bestellingsStatusId;
    }
}
