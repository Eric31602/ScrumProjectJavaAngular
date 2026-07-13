package be.vdab.scrumproject.inkomendeleveringslijnen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record NieuweLeveringsLijn(@NotBlank String ean,
                                  @Positive int aantalBesteld,
                                  @PositiveOrZero int aantalGoedgekeurd,
                                  @PositiveOrZero int aantalTeruggestuurd) {
}
