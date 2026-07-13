package be.vdab.scrumproject.leveranciers;

public class LeverancierNietgevonden extends RuntimeException {
  public LeverancierNietgevonden(String message) {
    super(message);
  }
}
