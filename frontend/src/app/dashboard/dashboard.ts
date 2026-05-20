import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  protected readonly features = [
    {
      title: 'Forecasts',
      description: 'Predict energy prices, demand curves, and profit margins using advanced forecasting models.',
      icon: '📈',
      link: '/forecasting',
    },
    {
      title: 'Comparisons',
      description: 'Compare forecast accuracy across models and time horizons to find the best strategy.',
      icon: '⚖️',
      link: '/comparisons',
    },
    {
      title: 'Monitoring',
      description: 'Monitor real-time status of energy systems — heat pumps, generators, solar panels, and storage.',
      icon: '⚡',
      link: '/monitoring',
    },
    {
      title: 'Simulation',
      description: 'Simulate trading scenarios with volatile prices and optimize dispatch schedules.',
      icon: '🔬',
      link: '/simulation',
    },
  ];

  protected readonly stats = [
    { value: '24/7', label: 'Market Monitoring' },
    { value: '< 2%', label: 'Forecast Error' },
    { value: '15+', label: 'Energy Assets' },
    { value: '€1.2M', label: 'Annual Savings' },
  ];
}
