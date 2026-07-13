package be.vdab.scrumproject.leveranciers;

public class LeverancierNietgevonden extends RuntimeException {
    public LeverancierNietgevonden() {
        super("Leverancier niet gevonden!");
    }
}
