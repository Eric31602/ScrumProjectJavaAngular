import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LeverancierIdNaam } from '../models/leverancier-id-naam';
import { LeveringLijnInfo } from '../models/levering-lijn-info';
import { InkomendeLeveringsInfo } from '../models/inkomende-leverings-info';
import { ArtikelSortOpLocatie } from '../models/artikel-sort-op-locatie';
import { Artikeldetails } from '../models/artikeldetails';
import { Bestellinginfo } from '../models/bestellinginfo';

@Injectable({
  providedIn: 'root',
})
export class LeveringenApi {

  private baseUrl = 'http://localhost:8080/leveranciers';
  private baseUrl2 = 'http://localhost:8080/leveringen';
  private bestellingen = 'http://localhost:8080/bestellingen';
  private bestellijnen = 'http://localhost:8080/bestellijnen';
  private artikels = 'http://localhost:8080/artikels';
  private retours = 'http://localhost:8080/retours';

  constructor(private http: HttpClient) {}

  getLeveranciers(): Observable<LeverancierIdNaam[]> {
    return this.http.get<LeverancierIdNaam[]>(this.baseUrl + '/findLeveranciers');
  }

  getLeveringsLijnen(id: number): Observable<LeveringLijnInfo[]> {
    return this.http.get<LeveringLijnInfo[]>(`${this.baseUrl2}/plaatsing/${id}`)
  }

  postInkomendeLevering(levering: InkomendeLeveringsInfo): Observable<number> {
    return this.http.post<number>(`${this.baseUrl2}/inkomend`, levering);
  }

  getBestellingVolgendeId(): Observable<number> {
    return this.http.get<number>(this.bestellingen + '/volgendeId');
  }

  getBestellijn(id: number): Observable<ArtikelSortOpLocatie[]> {
    return this.http.get<ArtikelSortOpLocatie[]>(`${this.bestellijnen}/${id}`);
  }

  postBestellingAfwerken(bestelId: number): Observable<number> {
    return this.http.post<number>(`${this.bestellingen}/afwerken/${bestelId}`, bestelId);
  }

  getArtikel(id: number): Observable<Artikeldetails> {
    return this.http.get<Artikeldetails>(`${this.artikels}/${id}`);
  }

  getAlleBestellingen(): Observable<Bestellinginfo[]> {
    return this.http.get<Bestellinginfo[]>(this.bestellingen + '/eersteVijf');
  }

  getTotaalAantalBestellingen(): Observable<number> {
    return this.http.get<number>(this.bestellingen + '/totaal');
  }

  putRetours(id: number): Observable<void> {
    return this.http.put<void>(`${this.retours}/start/${id}`, null);
  }

  getRetours(id: number): Observable<ArtikelSortOpLocatie[]> {
    return this.http.get<ArtikelSortOpLocatie[]>(`${this.retours}/${id}`);
  }

  putBeschadigd(id: number): Observable<void> {
    return this.http.put<void>(`${this.retours}/beschadigd/${id}`, null);
  }

  putRetourBevestig(id: number): Observable<void> {
    return this.http.put<void>(`${this.retours}/afwerken/${id}`, null);
  }
}
