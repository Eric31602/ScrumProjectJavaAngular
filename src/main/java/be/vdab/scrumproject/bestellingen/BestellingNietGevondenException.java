package be.vdab.scrumproject.bestellingen;

public class BestellingNietGevondenException extends RuntimeException {
  public BestellingNietGevondenException(String message) {
    super(message);
  }
}
