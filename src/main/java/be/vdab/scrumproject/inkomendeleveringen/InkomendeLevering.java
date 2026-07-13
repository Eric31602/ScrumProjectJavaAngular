package be.vdab.scrumproject.inkomendeleveringen;

import be.vdab.scrumproject.leveranciers.Leverancier;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inkomendeleveringen")
@NoArgsConstructor
public class InkomendeLevering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inkomendeLeveringsId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "leveranciersId")
    private Leverancier leverancier;
    private String leveringsbonNummer;
    private LocalDate leveringsbondatum;
    private LocalDate leverDatum;
    private long ontvangerPersoneelslidId;

    @Builder
    public InkomendeLevering(Leverancier leverancier, String leveringsbonNummer, LocalDate leveringsbondatum,
                             LocalDate leverDatum, long ontvangerPersoneelslidId) {
        this.leverancier = leverancier;
        this.leveringsbonNummer = leveringsbonNummer;
        this.leveringsbondatum = leveringsbondatum;
        this.leverDatum = leverDatum;
        this.ontvangerPersoneelslidId = ontvangerPersoneelslidId;
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public Leverancier getLeverancier() {
        return leverancier;
    }

    public String getLeveringsbonNummer() {
        return leveringsbonNummer;
    }

    public LocalDate getLeveringsbondatum() {
        return leveringsbondatum;
    }

    public LocalDate getLeverDatum() {
        return leverDatum;
    }

    public long getOntvangerPersoneelslidId() {
        return ontvangerPersoneelslidId;
    }
}
