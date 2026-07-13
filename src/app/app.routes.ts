import { Routes } from '@angular/router';
import { Leveranciers } from './leveranciers/leveranciers';
import { LeveringenFrontend } from './leveringen-frontend/leveringen-frontend';
import { Hoofdmenu } from './hoofdmenu/hoofdmenu';
import { Orderpicking } from './orderpicking/orderpicking';
import { Tvscherm } from './tvscherm/tvscherm';
import { Retours } from './retours/retours';

export const routes: Routes = [
    {path: 'leveringen-frontend', component: LeveringenFrontend},
    {path: 'leveringen/:id', component: Leveranciers},
    {path: 'hoofdmenu', component: Hoofdmenu},
    {path: '', redirectTo: 'hoofdmenu', pathMatch: 'full' },
    {path: 'orderpicking', component: Orderpicking},
    {path: 'tvscherm', component: Tvscherm},
    {path: 'retours', component: Retours}
];
