package be.vdab.scrumproject.artikelen;

import java.util.stream.Collectors;

public record ArtikelDetails(String ean, String naam, String beschrijving, int gewichtInGram,
                             int voorraad, String locatie) {
    public ArtikelDetails(Artikel artikel) {
        this(artikel.getEan(), artikel.getNaam(), artikel.getBeschrijving(), artikel.getGewichtInGram(),
                artikel.getVoorraad(), artikel.getMagazijnplaatsen().stream()
                        .map(m -> m.getRij() + m.getRek())
                                .collect(Collectors.joining(", ")));
    }
}
