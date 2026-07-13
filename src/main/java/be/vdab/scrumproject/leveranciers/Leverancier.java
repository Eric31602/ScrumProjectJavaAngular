package be.vdab.scrumproject.leveranciers;

import jakarta.persistence.*;

@Entity
@Table(name = "leveranciers")
public class Leverancier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leveranciersId;
    private String naam;
    private String btwNummer;
    private String straat;
    private String huisNummer;
    private String bus;
    private long plaatsId;
    private String familienaamContactpersoon;
    private String voornaamContactpersoon;

    public long getLeveranciersId() {
        return leveranciersId;
    }

    public String getNaam() {
        return naam;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNummer() {
        return huisNummer;
    }

    public String getBus() {
        return bus;
    }

    public long getPlaatsId() {
        return plaatsId;
    }

    public String getFamilienaamContactpersoon() {
        return familienaamContactpersoon;
    }

    public String getVoornaamContactpersoon() {
        return voornaamContactpersoon;
    }
}
