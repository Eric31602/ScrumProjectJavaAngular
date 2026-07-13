package be.vdab.scrumproject.bestellijnen;

public record ArtikelDetailsPerBestellijn(long artikelId, String naam, int aantalBesteld) {

    public ArtikelDetailsPerBestellijn(Bestellijn bestellijn) {
        this(bestellijn.getArtikel().getArtikelId(), bestellijn.getArtikel().getNaam(), bestellijn.getAantalBesteld());
    }
}
