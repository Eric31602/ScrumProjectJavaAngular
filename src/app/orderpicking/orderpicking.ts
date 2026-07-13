import { Component, inject, signal } from '@angular/core';
import { LeveringenApi } from '../apiService/leveringen-api';
import { ArtikelSortOpLocatie } from '../models/artikel-sort-op-locatie';
import { Artikeldetails } from '../models/artikeldetails';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-orderpicking',
  imports: [CommonModule, RouterModule],
  templateUrl: './orderpicking.html',
  styleUrl: './orderpicking.css',
})
export class Orderpicking {

  storing = signal(false);
  bestelId = signal<number | null>(null);
  bestellijnen = signal<ArtikelSortOpLocatie[]>([]);
  artikelInfo = signal<Artikeldetails | null>(null);
  selectedArtikelId = signal<number | null>(null);
  checkedArtikelen = signal(new Set<number>());

  private apiService = inject(LeveringenApi);

  ngOnInit(): void {
    this.laadVolgendeBestelling();
  }

  laadVolgendeBestelling(): void {
    this.storing.set(false);
    this.apiService.getBestellingVolgendeId().subscribe({
        next: (id) => {
          this.bestelId.set(id);
          this.laadBestellijnen(id);
        },
        error: () => {
          this.storing.set(true);
        }
      });
  }

  laadBestellijnen(id: number): void {
    this.apiService.getBestellijn(id).subscribe({
      next: (bestellijnen) => {
        this.bestellijnen.set(bestellijnen);
      },
      error: () => {
        this.storing.set(true);
      }
    })
  }

  toonArtikelInfo(artikelId: number): void {

    if (this.selectedArtikelId() === artikelId) {
      this.selectedArtikelId.set(null);
      this.artikelInfo.set(null);
      return;
    }

    this.selectedArtikelId.set(artikelId);

    this.apiService.getArtikel(artikelId).subscribe({
      next: (artikel) => {
        this.artikelInfo.set(artikel);
      },
      error: () => {
        this.storing.set(true);
      }
    })
  }

  werkBestellingAf(): void {
    const id = this.bestelId();

    if (id === null) {
      return;
    }

    this.apiService.postBestellingAfwerken(id).subscribe({
      next: () => {
        this.laadVolgendeBestelling();
      },
      error: () => {
        this.storing.set(true);
      }
    });
  }

  toggleCheckbox(artikelId: number, checked: boolean): void {
    const nieuweSet = new Set(this.checkedArtikelen());

    if (checked) {
      nieuweSet.add(artikelId);
    } else {
      nieuweSet.delete(artikelId);
    }

    this.checkedArtikelen.set(nieuweSet);
  }

  alleCheckboxenAangevinkt(): boolean {
    return (this.checkedArtikelen().size === this.bestellijnen().length);
  }
}
