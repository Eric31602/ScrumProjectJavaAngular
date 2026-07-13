package be.vdab.scrumproject.leveranciers;

public record LeverancierInfo(long leveranciersId, String naam) {

    public LeverancierInfo(Leverancier leverancier) {
        this(leverancier.getLeveranciersId(), leverancier.getNaam());
    }
}
