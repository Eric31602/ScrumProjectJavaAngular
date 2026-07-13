package be.vdab.scrumproject.bestellingen;

import java.time.LocalDateTime;

public record BestellingInfo(long bestelId, LocalDateTime besteldatum, long aantalArtikelen,
                             double totaalGewichtKg) {


}
