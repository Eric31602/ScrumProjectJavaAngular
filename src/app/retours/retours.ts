import { Component, inject, signal } from '@angular/core';
import { LeveringenApi } from '../apiService/leveringen-api';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ArtikelSortOpLocatie } from '../models/artikel-sort-op-locatie';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-retours',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './retours.html',
  styleUrl: './retours.css',
})
export class Retours {

  private apiService = inject(LeveringenApi);
  private fb = inject(FormBuilder);

  bestelId = signal<number | null>(null);
  storing = signal(false);
  retours = signal<ArtikelSortOpLocatie[]>([]);
  tonen = signal(false);
  checkedRetours = signal(new Set<number>());
  checkbox = signal(false);

  form = this.fb.group({
    id: this.fb.control<number | null>(null, Validators.required),
    beschadigd: this.fb.control(false)
  });

  zoekRetours(): void {
    this.storing.set(false);

    this.apiService.getRetours(this.form.value.id!).subscribe({
      next: (retour) => {
        this.retours.set(retour);
      },
      error: () => {
        this.storing.set(true);
        this.tonen.set(false);
      }
    });
    this.tonen.set(true);
  }

  wijzigRetour(): void {
    this.storing.set(false);

    this.apiService.putBeschadigd(this.form.value.id!).subscribe({
      next: () => {
        console.log('Retour status gewijzigd naar beschadigd!');
      },
      error: () => {
        this.storing.set(true);
      }
    });
  }

  toggleCheckbox(artikelId: number, checked: boolean): void {
    const nieuweSet = new Set(this.checkedRetours());

    if (checked) {
      nieuweSet.add(artikelId);
    } else {
      nieuweSet.delete(artikelId);
    }

    this.checkedRetours.set(nieuweSet);
  }

  alleCheckboxenAangevinkt(): boolean {
    return (this.checkedRetours().size === this.retours().length);
  }

  bevestigRetour(): void {
    this.storing.set(false);

    this.apiService.putRetourBevestig(this.form.value.id!).subscribe({
      next: () => {
        console.log('Retour succesvol aangepast!');
      },
      error: () => {
        this.storing.set(true);
      }
    });
  }

  afronden(): void {
    if (this.form.value.beschadigd) {
      this.wijzigRetour();
    } else {
      this.bevestigRetour();
    }
  }

}
