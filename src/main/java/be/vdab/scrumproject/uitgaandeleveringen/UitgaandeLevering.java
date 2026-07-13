package be.vdab.scrumproject.uitgaandeleveringen;

import be.vdab.scrumproject.bestellingen.Bestelling;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "uitgaandeleveringen")
public class UitgaandeLevering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uitgaandeLeveringsId;

    @ManyToOne
    @JoinColumn(name = "bestelId")
    private Bestelling bestelling;

    private LocalDate vertrekDatum;
    private LocalDate aankomstDatum;
    private String trackingcode;
    private long klantId;
    private long uitgaandeLeveringsStatusId;

    public UitgaandeLevering(Bestelling bestelling, LocalDate vertrekDatum, LocalDate aankomstDatum,
                             String trackingcode, long klantId, long uitgaandeLeveringsStatusId) {
        this.bestelling = bestelling;
        this.vertrekDatum = vertrekDatum;
        this.aankomstDatum = aankomstDatum;
        this.trackingcode = trackingcode;
        this.klantId = klantId;
        this.uitgaandeLeveringsStatusId = uitgaandeLeveringsStatusId;
    }

    protected UitgaandeLevering() {}

    public long getUitgaandeLeveringsId() {
        return uitgaandeLeveringsId;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public LocalDate getVertrekDatum() {
        return vertrekDatum;
    }

    public LocalDate getAankomstDatum() {
        return aankomstDatum;
    }

    public String getTrackingcode() {
        return trackingcode;
    }

    public long getKlantId() {
        return klantId;
    }

    public long getUitgaandeLeveringsStatusId() {
        return uitgaandeLeveringsStatusId;
    }


}
