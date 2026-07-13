import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LeveringenApi } from '../apiService/leveringen-api';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { LeveringLijnInfo } from '../models/levering-lijn-info';
import { map, switchMap, catchError, of } from 'rxjs';


@Component({
  selector: 'app-leveranciers',
  imports: [],
  templateUrl: './leveranciers.html',
  styleUrl: './leveranciers.css',
})
export class Leveranciers {

  private route = inject(ActivatedRoute);
  private api = inject(LeveringenApi);

  error = signal<string | null>(null);

 id = toSignal(this.route.paramMap.pipe(
  map(params => Number(params.get('id')))
 ),
 {initialValue: 0}
);

leveringen = toSignal(
  toObservable(this.id).pipe(
    switchMap(id =>
      this.api.getLeveringsLijnen(id).pipe(
        catchError(error => {
          console.error('Failed to load', error);

          this.error.set('Kon gegevens niet laden');

          return of([] as LeveringLijnInfo[]);
        })
      )
    )
  ),
  { initialValue: [] as LeveringLijnInfo[] }
);
}
