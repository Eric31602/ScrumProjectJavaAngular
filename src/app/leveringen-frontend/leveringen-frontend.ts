import { Component, inject, signal } from '@angular/core'; 
import { CommonModule, DatePipe } from '@angular/common'; 
import { LeveringenApi } from '../apiService/leveringen-api'; 
import { LeverancierIdNaam } from '../models/leverancier-id-naam'; 
import { ChangeDetectorRef } from '@angular/core';  
import { toSignal } from '@angular/core/rxjs-interop';
import { catchError, of, firstValueFrom } from 'rxjs';
import { ReactiveFormsModule, FormBuilder, Validators, FormArray, FormGroup } from '@angular/forms';
import { InkomendeLeveringsInfo } from '../models/inkomende-leverings-info';
import { LeveringLijnInfo } from '../models/levering-lijn-info';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-leveringen-frontend',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  providers: [DatePipe],
  templateUrl: './leveringen-frontend.html',
  styleUrl: './leveringen-frontend.css',
})
export class LeveringenFrontend {

private fb = inject(FormBuilder);
private datePipe = inject(DatePipe);

form = this.fb.group({
  naam: ['', Validators.required],
  bonNummer: ['', Validators.required],
  leverDatum: [this.datePipe.transform(new Date(), 'yyyy-MM-dd'), Validators.required],
  lijnen: this.fb.array([this.maakLijn()])
});

private apiService = inject(LeveringenApi);
   error = signal<string | null>(null);
   errorLijnen = signal<string | null>(null);

   huidigeSectie = signal<'invoer' | 'resultaat'>('invoer');
   overzicht = signal<LeveringLijnInfo[]>([]);
   overzichtChecks = signal<boolean[]>([]);

  leveranciers = toSignal(
    this.apiService.getLeveranciers().pipe(
      catchError(error => {console.error('Failed to load!', error);
        this.error.set('Kon gegevens niet laden!');
        
        return of<LeverancierIdNaam[]>([]);
      })
    ),
    {initialValue: [] as LeverancierIdNaam[]}
  );

  get lijnen(): FormArray {
    return this.form.get('lijnen') as FormArray;
  }
  
  private maakLijn(): FormGroup {
    return this.fb.group({
      ean: ['', Validators.required],
      aantalBesteld: [1, [Validators.required, Validators.min(1)]],
      aantalGoedgekeurd: [0, [Validators.required, Validators.min(0)]],
      aantalTeruggestuurd: [0, [Validators.required, Validators.min(0)]]
    })
  }

  voegRijToe(): void {
    this.lijnen.push(this.maakLijn());
  }

  verwijderRij(index: number): void {
    this.lijnen.removeAt(index)
  }

  async opslaan(): Promise<void> {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.error.set(null);
    this.errorLijnen.set(null)

    const payload: InkomendeLeveringsInfo = {
      leverancierNaam: this.form.value.naam!,
      leveringsbonNummer: this.form.value.bonNummer!,
      leveringsbondatum: this.form.value.leverDatum!,
      nieuweLeveringsLijnen: this.lijnen.controls.map(lijn => ({
      ean: lijn.value.ean,
      aantalBesteld: Number(lijn.value.aantalBesteld),
      aantalGoedgekeurd: Number(lijn.value.aantalGoedgekeurd),
      aantalTeruggestuurd: Number(lijn.value.aantalTeruggestuurd)
    }))

  };

   try {
      const leveringId = await firstValueFrom(this.apiService.postInkomendeLevering(payload));
      await this.laadWegzetInstructies(leveringId);
    } catch (e) {
      console.error(e);
      this.error.set('Opslaan mislukt, probeer opnieuw.');
    }
  }


  private async laadWegzetInstructies(id: number): Promise<void> {
    try {
      const data = await firstValueFrom(this.apiService.getLeveringsLijnen(id));
      this.overzicht.set(data);
      this.overzichtChecks.set(data.map(() => false));
      this.huidigeSectie.set('resultaat');
    } catch (e) {
      console.error(e);
      this.errorLijnen.set('Kon de wegzetinstructies niet laden.');
    }
  }

  toggleCheck(index: number): void {
    this.overzichtChecks.update(checks => {
      const copy = [...checks];
      copy[index] = !copy[index];
      return copy;
    })
  }




}