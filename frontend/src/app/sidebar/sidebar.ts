import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {
  protected readonly navItems = [
    { label: 'Forecasts', path: '/forecasting' },
    { label: 'Comparisons', path: '/comparisons' },
    { label: 'Monitoring', path: '/monitoring' },
    { label: 'Simulation', path: '/simulation' },
  ];
}
