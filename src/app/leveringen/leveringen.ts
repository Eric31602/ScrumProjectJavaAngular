import { Component, inject, signal } from '@angular/core'; 
import { CommonModule } from '@angular/common'; 
import { LeveringenApi } from '../apiService/leveringen-api'; 
import { LeverancierIdNaam } from '../models/leverancier-id-naam'; 
import { ChangeDetectorRef } from '@angular/core';  
import { toSignal } from '@angular/core/rxjs-interop';
import { catchError, of } from 'rxjs';

@Component({   
  selector: 'app-leveringen',   
  standalone: true,   
  imports: [CommonModule],   
  templateUrl: './leveringen.html', 
}) 
  export class Leveringen {   

   
    }