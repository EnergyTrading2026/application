import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./dashboard/dashboard').then((m) => m.Dashboard),
    data: { showSidebar: false },
  },
  {
    path: 'forecasting',
    loadComponent: () =>
      import('./forecasting/forecasting').then((m) => m.Forecasting),
    data: { title: 'Forecasting', showSidebar: true },
  },
  {
    path: 'comparisons',
    loadComponent: () =>
      import('./placeholder/placeholder').then((m) => m.Placeholder),
    data: { title: 'Comparisons', showSidebar: true },
  },
  {
    path: 'monitoring',
    loadComponent: () =>
      import('./monitoring/monitoring').then((m) => m.Monitoring),
    data: { title: 'Monitoring', showSidebar: true },
  },
  {
  path: 'simulation',
  loadComponent: () =>
    import('./simulation/simulation').then((m) => m.Simulation),
  data: { showSidebar: true },
  },
];
