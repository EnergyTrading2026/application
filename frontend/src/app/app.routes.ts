import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./dashboard/dashboard').then((m) => m.Dashboard),
  },
  {
    path: 'forecasting',
    loadComponent: () =>
      import('./forecasting/forecasting').then((m) => m.Forecasting),
    data: { title: 'Forecasting' },
  },
  {
    path: 'comparisons',
    loadComponent: () =>
      import('./placeholder/placeholder').then((m) => m.Placeholder),
    data: { title: 'Comparisons' },
  },
  {
    path: 'status',
    loadComponent: () =>
      import('./placeholder/placeholder').then((m) => m.Placeholder),
    data: { title: 'Status' },
  },
  {
    path: 'simulation',
    loadComponent: () =>
      import('./placeholder/placeholder').then((m) => m.Placeholder),
    data: { title: 'Simulation' },
  },
];
