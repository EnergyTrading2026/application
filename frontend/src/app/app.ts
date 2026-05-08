import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly navItems = [
    { label: 'Dashboard', path: '/dashboard' },
    { label: 'Forecasts', path: '/forecasting' },
    { label: 'Comparisons', path: '/comparisons' },
    { label: 'Status', path: '/status' },
    { label: 'Simulation', path: '/simulation' },
  ];
}
