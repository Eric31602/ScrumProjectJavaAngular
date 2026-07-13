package be.vdab.scrumproject.bestellingen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BestellingNietGevondenException extends RuntimeException {
    public BestellingNietGevondenException() {
        super("Geen bestelling gevonden!");
    }
}
