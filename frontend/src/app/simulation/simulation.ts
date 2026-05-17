import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
// ─── Shared geometry types ────────────────────────────────────────────────────
interface Box  { x: number; y: number; width: number; height: number; }
interface Circ { cx: number; cy: number; r: number; }
interface Pt   { x: number; y: number; }
 
// ─── Menu-entry descriptor ────────────────────────────────────────────────────
export interface MenuEntry {
  key:   string;
  label: string;
  unit:  string;   // e.g. "MW"  – empty string if no unit
  sub:   string;   // subscript after unit, e.g. "el" – empty string if none
  extra: string;   // anything after the subscript, e.g. " / MW"
  sub2:  string;   // second subscript, e.g. "Hs"
}
 
@Component({
  selector: 'app-simulation',
  imports: [CommonModule, FormsModule],
  templateUrl: './simulation.html',
  styleUrl: './simulation.css',
})
export class Simulation {
 
  // ─── Popup ────────────────────────────────────────────────────────────────
  activePopup: { key: string; label: string } | null = null;
  tempValue = 0;
 
  openPopup(key: string, label: string) {
    this.tempValue = this.params[key];
    this.activePopup = { key, label };
  }
  confirmPopup() {
    if (this.activePopup) this.params[this.activePopup.key] = Number(this.tempValue);
    this.activePopup = null;
  }
  cancelPopup() { this.activePopup = null; }
 
  // ─── Component parameters ───────────────────────────────────
  params: Record<string, number> = {
    // Gas Grid
    GasPrice: 35,
    CO2Factor: 0.201,
    CO2Price: 60,

    // Heat Pump
    RatedElectricalPower: 8,
    MinPower: 1,
    COP: 3.5,

    // CHP
    CHPElectricalPowerMax: 6,
    CHPElectricalPowerMin: 2,
    CHPThermalPowerMax: 7,
    CHPElectricalEfficiency: 0.4,
    CHPThermalEfficiency: 0.48,
    CHPOverallEfficiency: 0.88,
    CHPMinRunTime: 8,
    CHPMinDowntime: 8,
    CHPStartupCosts: 600,

    // Boiler
    RatedThermalPower: 12,
    BoilerMinLoad: 2,
    BoilerEfficiency: 0.97,
    BoilerMinRunTime: 1,
    BoilerMinDowntime: 1,

    // Thermal Storage
    StorageCapacity: 200,
    MaxChargingPower: 15,
    MaxDischargingPower: 15,
    StorageLoss: 0.000125,
    InitialSoC: 200,
  };

  //  ─── Component Colors ─────────────────────────────────────────────────────────
  ThermalColor = "#e05d5d"
  ElectricityColor = "#eceded"
  GasColor = "#898C92"

  // ─── Toggle state ─────────────────────────────────────────────────────────
  HeatPumpOn       = true;
  CHPOn            = true;
  BoilerOn         = true;
  ThermalStorageOn = true;
 
  showGasGridMenu        = false;
  showHeatPumpMenu       = false;
  showCHPMenu            = false;
  showBoilerMenu         = false;
  showThermalStorageMenu = false;
 
  /** Single toggle method used by all dropdown buttons via .bind(this, key) */
  toggleMenu(key: 'showGasGridMenu' | 'showHeatPumpMenu' | 'showCHPMenu' | 'showBoilerMenu' | 'showThermalStorageMenu') {
    this[key] = !this[key];
  }
 
  // ─── Geometry ─────────────────────────────────────────────────────────────
  PowerGridCircle:      Circ = { cx: 150, cy: 250, r: 50 };
  GasGridCircle:        Circ = { cx: 150, cy: 550, r: 50 };
  HeatPumpBox:          Box  = { x: 300, y: 40,  width: 115, height: 185 };
  CHPBox1:              Box  = { x: 300, y: 320, width: 115, height: 92.5 };
  CHPBox2:              Box  = { x: 300, y: 320 + 92.5, width: 115, height: 92.5 };
  BoilerBox:            Box  = { x: 300, y: 600, width: 115, height: 185 };
  OrCircle:             Circ = { cx: 500, cy: 320 + 92.5, r: 20 };
  ThermalStorageBox:    Box  = { x: 650, y: 180, width: 115, height: 185 };
  ThermalLegendBox:     Box  = { x: 850, y: 650, width: 115, height: 60 };
  ElectricityLegendBox: Box  = { x: 850, y: 720, width: 115, height: 60 };
  GasLegendBox:         Box  = { x: 850, y: 790, width: 115, height: 60 };
 
  get HeatingNetworkCircle(): Circ {
    return { cx: this.ThermalStorageBox.x + this.ThermalStorageBox.width / 2, cy: 550, r: 50 };
  }
 
  // ─── Geometry helpers ─────────────────────────────────────────────────────
  /** Returns the midpoint of a named box edge */
  edge(b: Box, side: 'left' | 'right' | 'top' | 'bottom'): Pt {
    switch (side) {
      case 'left':   return { x: b.x,             y: b.y + b.height / 2 };
      case 'right':  return { x: b.x + b.width,   y: b.y + b.height / 2 };
      case 'top':    return { x: b.x + b.width / 2, y: b.y };
      case 'bottom': return { x: b.x + b.width / 2, y: b.y + b.height };
    }
  }
 
  /** Returns a point on a circle's perimeter at a given angle in degrees */
  circPt(c: Circ, angleDeg: number): Pt {
    const rad = angleDeg * Math.PI / 180;
    return { x: c.cx + c.r * Math.cos(rad), y: c.cy + c.r * Math.sin(rad) };
  }
 
  // Named circle points (used in template)
  get PowerGridTop()           { return this.circPt(this.PowerGridCircle, -90); }
  get GasGridTop()             { return this.circPt(this.GasGridCircle,   -90); }
  get GasGridBottom()             { return this.circPt(this.GasGridCircle,   +90); }
  get HeatPumpLeft()           { return this.edge(this.HeatPumpBox,    'left');   }
  get HeatPumpRight()          { return this.edge(this.HeatPumpBox,    'right');  }
  get CHPLeft()                { return { x: this.CHPBox1.x, y: this.CHPBox1.y + this.CHPBox1.height }; }
  get CHPRight()               { return { x: this.CHPBox1.x + this.CHPBox1.width, y: this.CHPBox1.y + this.CHPBox1.height }; }
  get BoilerLeft()             { return this.edge(this.BoilerBox, 'left');  }
  get BoilerRight()            { return this.edge(this.BoilerBox, 'right'); }
  get ThermalStorageLeft()     { return this.edge(this.ThermalStorageBox, 'left');   }
  get ThermalStorageBottom()   { return this.edge(this.ThermalStorageBox, 'bottom'); }
  get HeatingNetworkTop()      { return this.circPt(this.HeatingNetworkCircle, -90); }
  get HeatingNetworkTopLeft()  {
    return {
      x: this.HeatingNetworkCircle.cx - this.HeatingNetworkCircle.r * Math.cos(45 * Math.PI / 180),
      y: this.HeatingNetworkCircle.cy - this.HeatingNetworkCircle.r * Math.sin(45 * Math.PI / 180),
    };
  }
 
  get OrTop()         { return this.circPt(this.OrCircle, -90); }
  get OrBottom()      { return this.circPt(this.OrCircle,  90); }
  get OrLeft()        { return this.circPt(this.OrCircle, 180); }
  get OrTopRight()    { return this.circPt(this.OrCircle, -45); }
  get OrBottomRight() { return this.circPt(this.OrCircle,  45); }
 
  // ─── Menu entry definitions ───────────────────────────────────────────────
  gasGridEntries: MenuEntry[] = [
    { key: 'GasPrice',  label: 'Gas Price (€ / MWh Hs)',           unit: '€ / MWh', sub: 'Hs', extra: '', sub2: '' },
    { key: 'CO2Factor', label: 'CO₂ Factor (tCO₂ / MWh Hs)',       unit: 'tCO₂ / MWh', sub: 'Hs', extra: '', sub2: '' },
    { key: 'CO2Price',  label: 'CO₂ Price (€ / tCO₂)',             unit: '€ / tCO₂',   sub: '',   extra: '', sub2: '' },
  ];
 
  heatPumpEntries: MenuEntry[] = [
    { key: 'RatedElectricalPower', label: 'Rated Electrical Power (MW el)', unit: 'MW', sub: 'el', extra: '', sub2: '' },
    { key: 'MinPower',             label: 'Min. Power (MW el)',              unit: 'MW', sub: 'el', extra: '', sub2: '' },
    { key: 'COP',                  label: 'COP (MW th / MW el)',             unit: 'MW', sub: 'th', extra: ' / MW', sub2: 'el' },
  ];
 
  chpEntries: MenuEntry[] = [
    { key: 'CHPElectricalPowerMax',   label: 'Electrical Power max (MW el)',          unit: 'MW', sub: 'el', extra: '', sub2: '' },
    { key: 'CHPElectricalPowerMin',   label: 'Electrical Power min (MW el)',          unit: 'MW', sub: 'el', extra: '', sub2: '' },
    { key: 'CHPThermalPowerMax',      label: 'Thermal Power max (MW th)',             unit: 'MW', sub: 'th', extra: '', sub2: '' },
    { key: 'CHPElectricalEfficiency', label: 'Electrical Efficiency (MW el / MW Hs)',  unit: 'MW', sub: 'el', extra: ' / MW', sub2: 'Hs' },
    { key: 'CHPThermalEfficiency',    label: 'Thermal Efficiency (MW th / MW Hs)',     unit: 'MW', sub: 'th', extra: ' / MW', sub2: 'Hs' },
    { key: 'CHPOverallEfficiency',    label: 'Overall Efficiency',                     unit: '',   sub: '',   extra: '', sub2: '' },
    { key: 'CHPMinRunTime',           label: 'Min. Run Time (Intervals)',              unit: 'Intervals', sub: '', extra: '', sub2: '' },
    { key: 'CHPMinDowntime',          label: 'Min. Downtime (Intervals)',              unit: 'Intervals', sub: '', extra: '', sub2: '' },
    { key: 'CHPStartupCosts',         label: 'Start-up Costs (€)',                     unit: '€',  sub: '',   extra: '', sub2: '' },
  ];
 
  boilerEntries: MenuEntry[] = [
    { key: 'RatedThermalPower', label: 'Rated Thermal Power (MW th)',      unit: 'MW', sub: 'th', extra: '', sub2: '' },
    { key: 'BoilerMinLoad',     label: 'Min. Load (MW th)',                unit: 'MW', sub: 'th', extra: '', sub2: '' },
    { key: 'BoilerEfficiency',  label: 'Efficiency (MW th / MW Hs)',       unit: 'MW', sub: 'th', extra: ' / MW', sub2: 'Hs' },
    { key: 'BoilerMinRunTime',  label: 'Min. Run Time (h)',                unit: 'h',  sub: '',   extra: '', sub2: '' },
    { key: 'BoilerMinDowntime', label: 'Min. Downtime (h)',                unit: 'h',  sub: '',   extra: '', sub2: '' },
  ];
 
  thermalStorageEntries: MenuEntry[] = [
    { key: 'StorageCapacity',      label: 'Storage Capacity (MWh th)',          unit: 'MWh', sub: 'th', extra: '', sub2: '' },
    { key: 'MaxChargingPower',     label: 'Max. Charging Power (MW th)',        unit: 'MW',  sub: 'th', extra: '', sub2: '' },
    { key: 'MaxDischargingPower',  label: 'Max. Discharging Power (MW th)',     unit: 'MW',  sub: 'th', extra: '', sub2: '' },
    { key: 'StorageLoss',          label: 'Loss (MWh th / 15 min)',             unit: 'MWh', sub: 'th', extra: ' / 15 min', sub2: '' },
    { key: 'InitialSoC',           label: 'Initial State of Charge (MWh th)',  unit: 'MWh', sub: 'th', extra: '', sub2: '' },
  ];
 
  // ─── Helpers used in template ─────────────────────────────────────────────
 
  /** Convert a Pt to the "x,y" */
  pt(p: Pt): string { return `${p.x},${p.y}`; }
 
  /** Height for a dropdown menu rect based on number of entries */
  menuHeight(entries: MenuEntry[]): number { return entries.length * 25 + 15; }
}