import { Component, signal, computed, ElementRef, viewChild, afterNextRender } from '@angular/core';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

type DataCategory = 'energyPrice' | 'demand' | 'profit';
type TimeRange = 'dayAhead' | '7days' | '30days';

interface DataSet {
  label: string;
  unit: string;
  data: Record<TimeRange, { labels: string[]; values: number[] }>;
}

const MOCK_DATA: Record<DataCategory, DataSet> = {
  energyPrice: {
    label: 'Energy Price',
    unit: '€/MWh',
    data: {
      dayAhead: {
        labels: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00'],
        values: [45.2, 42.8, 68.5, 72.1, 58.3, 48.9],
      },
      '7days': {
        labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        values: [52.3, 58.1, 61.4, 55.8, 49.2, 43.5, 47.8],
      },
      '30days': {
        labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
        values: [55.2, 52.8, 48.5, 51.1],
      },
    },
  },
  demand: {
    label: 'Demand',
    unit: 'GW',
    data: {
      dayAhead: {
        labels: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00'],
        values: [42.1, 38.5, 55.2, 62.8, 58.4, 48.2],
      },
      '7days': {
        labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        values: [58.2, 61.5, 59.8, 62.1, 56.3, 45.8, 43.2],
      },
      '30days': {
        labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
        values: [56.8, 58.2, 54.5, 57.1],
      },
    },
  },
  profit: {
    label: 'Profit',
    unit: '€/MWh',
    data: {
      dayAhead: {
        labels: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00'],
        values: [8.5, 6.2, 15.8, 18.4, 12.1, 9.8],
      },
      '7days': {
        labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        values: [12.5, 14.8, 16.2, 13.5, 10.8, 8.2, 9.5],
      },
      '30days': {
        labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
        values: [13.2, 12.5, 10.8, 11.5],
      },
    },
  },
};

@Component({
  selector: 'app-forecasting',
  templateUrl: './forecasting.html',
  styleUrl: './forecasting.css',
})
export class Forecasting {
  protected readonly categories: { key: DataCategory; label: string }[] = [
    { key: 'energyPrice', label: 'Energy Price' },
    { key: 'demand', label: 'Demand' },
    { key: 'profit', label: 'Profit' },
  ];

  protected readonly timeRanges: { key: TimeRange; label: string }[] = [
    { key: 'dayAhead', label: 'Day-ahead' },
    { key: '7days', label: '7 days' },
    { key: '30days', label: '30 days' },
  ];

  protected readonly activeCategory = signal<DataCategory>('energyPrice');
  protected readonly activeTimeRange = signal<TimeRange>('7days');

  protected readonly chartData = computed(() => {
    const category = this.activeCategory();
    const range = this.activeTimeRange();
    return MOCK_DATA[category].data[range];
  });

  protected readonly chartUnit = computed(() => {
    return MOCK_DATA[this.activeCategory()].unit;
  });

  private readonly chartCanvas = viewChild<ElementRef<HTMLCanvasElement>>('chartCanvas');
  private chart: Chart | null = null;

  constructor() {
    afterNextRender(() => {
      this.createChart();
    });
  }

  selectCategory(key: DataCategory): void {
    this.activeCategory.set(key);
    this.updateChart();
  }

  selectTimeRange(key: TimeRange): void {
    this.activeTimeRange.set(key);
    this.updateChart();
  }

  private createChart(): void {
    const canvas = this.chartCanvas()?.nativeElement;
    if (!canvas) return;

    const data = this.chartData();
    const unit = this.chartUnit();

    this.chart = new Chart(canvas, {
      type: 'line',
      data: {
        labels: data.labels,
        datasets: [
          {
            data: data.values,
            borderColor: '#6b7280',
            backgroundColor: 'rgba(107, 114, 128, 0.1)',
            borderWidth: 2,
            pointBackgroundColor: '#6b7280',
            pointBorderColor: '#6b7280',
            pointRadius: 5,
            pointHoverRadius: 7,
            tension: 0.3,
            fill: true,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: { display: false },
          tooltip: {
            callbacks: {
              label: (ctx) => `${ctx.parsed.y} ${unit}`,
            },
          },
        },
        scales: {
          y: {
            beginAtZero: true,
            title: {
              display: true,
              text: unit,
              color: '#9ca3af',
            },
            grid: {
              color: 'rgba(156, 163, 175, 0.2)',
            },
            border: {
              dash: [4, 4],
            },
            ticks: {
              color: '#9ca3af',
            },
          },
          x: {
            grid: {
              display: false,
            },
            ticks: {
              color: '#9ca3af',
            },
          },
        },
      },
    });
  }

  private updateChart(): void {
    if (!this.chart) return;

    const data = this.chartData();
    const unit = this.chartUnit();

    this.chart.data.labels = data.labels;
    this.chart.data.datasets[0].data = data.values;
    const yScale = this.chart.options!.scales!['y'] as Record<string, unknown>;
    yScale['title'] = {
      display: true,
      text: unit,
      color: '#9ca3af',
    };
    this.chart.options!.plugins!.tooltip = {
      callbacks: {
        label: (ctx) => `${ctx.parsed.y} ${unit}`,
      },
    };
    this.chart.update();
  }
}
