package be.vdab.scrumproject.inkomendeleveringen;

import be.vdab.scrumproject.inkomendeleveringslijnen.NieuweLeveringsLijn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record InkomendeLeveringInfo(@NotBlank String leverancierNaam,
                                    @NotBlank String leveringsbonNummer,
                                    @NotNull LocalDate leveringsbondatum,
                                    @NotEmpty List<@Valid NieuweLeveringsLijn> nieuweLeveringsLijnen) {
}
