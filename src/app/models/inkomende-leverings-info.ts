import { NieuweLeveringsLijn } from "./nieuwe-leverings-lijn";

export interface InkomendeLeveringsInfo {
    leverancierNaam: string;
    leveringsbonNummer: string;
    leveringsbondatum: string;
    nieuweLeveringsLijnen: NieuweLeveringsLijn[];
}
