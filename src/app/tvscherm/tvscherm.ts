import { Component, inject, DestroyRef, signal } from '@angular/core';
import { LeveringenApi } from '../apiService/leveringen-api';
import { interval, startWith, switchMap } from 'rxjs';
import { takeUntilDestroyed, toSignal } from '@angular/core/rxjs-interop';
import { Bestellinginfo } from '../models/bestellinginfo';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tvscherm',
  imports: [CommonModule],
  templateUrl: './tvscherm.html',
  styleUrl: './tvscherm.css',
})
export class Tvscherm {

  private apiService = inject(LeveringenApi);
  private destroyRef = inject(DestroyRef);

  bestellingen = signal<Bestellinginfo[]>([]);
  storing = signal(false);
  totaalBestellingen = toSignal(
    this.apiService.getTotaalAantalBestellingen(),
    {initialValue: 0}
  );

  ngOnInit(): void {
    this.laadBestellingen();
  }

  laadBestellingen(): void {
    interval(5 * 60 * 1000)
    .pipe(
      startWith(0),
      switchMap(() => this.apiService.getAlleBestellingen()),
      takeUntilDestroyed(this.destroyRef)
    )
    .subscribe({
      next: (orders) => {
        this.bestellingen.set(orders)
      },
      error: () => {
      this.storing.set(true);
    }
    });
  }

  
}
