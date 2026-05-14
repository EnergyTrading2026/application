import { Component } from '@angular/core';
import * as model from './monitoring.model';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import mockData from './monitoring.json';
@Component({
  selector: 'app-monitoring',
  imports: [MatCardModule, MatChipsModule, MatDividerModule, MatIconModule, MatToolbarModule],
  templateUrl: './monitoring.html',
  styleUrl: './monitoring.css',
})
export class Monitoring {
  protected readonly system: model.System = mockData.system;
  protected readonly storage: model.Storage = mockData.storage;
  protected readonly assets: model.Asset[] = mockData.assets as model.Asset[];

  isCHP(asset: model.Asset): asset is model.CHP {
    return asset.type === 'chp';
  }

  netCost(asset: model.Asset): number {
    return this.isCHP(asset) ? asset.inputCost - asset.electricalOutputRevenue : asset.inputCost;
  }

  get totalCostPerHour(): number {
    return this.assets.reduce((sum, asset) => sum + this.netCost(asset), 0);
  }

  get networkSupply(): number {
    return this.assets.reduce((sum, asset) => sum + asset.networkOutput, 0);
  }

  storageCharging(asset: model.Asset): number {
    return asset.active ? asset.thermalOutput - asset.networkOutput : 0;
  }

  assetColor(asset: model.Asset): string {
    const colors: Record<model.AssetType, string> = {
      heatPump: '#0d9488',
      chp: '#d97706',
      boiler: '#6b7280',
    };
    return colors[asset.type];
  }
}
