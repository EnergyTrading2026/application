import { Component } from '@angular/core';

@Component({
  selector: 'app-simulation',
  imports: [],
  templateUrl: './simulation.html',
  styleUrl: './simulation.css',
})
export class Simulation {
  PowerGridCircle = { cx: 150, cy: 250, r: 50 };
  GasGridCircle = { cx: 150, cy: 550, r: 50 };

  HeatPumpBox = { x: 300, y: 40, width: 115, height: 185 };
  CHPBox1 = { x: 300, y: 320, width: 115, height: 92.5 };
  CHPBox2 = { x: 300, y: 320 + 92.5, width: 115, height: 92.5 };
  BoilerBox = { x: 300, y: 600, width: 115, height: 185 };

  OrCircle = { cx: 500, cy: 320 + 92.5, r: 20 };

  ThermalStorageBox = { x: 650, y: 180, width: 115, height: 185 };
  HeatingNetworkCircle = { cx: 650 + (115 / 2), cy: 550, r: 50 };
  
  ThermalLegendBox = { x: 850, y: 650, width: 115, height: 60 };
  ElectricityLegendBox = { x: 850, y: 720, width: 115, height: 60 };
  GasLegendBox = { x: 850, y: 790, width: 115, height: 60 };

  get PowerGridCircleTop() { return { x: this.PowerGridCircle.cx, y: this.PowerGridCircle.cy - this.PowerGridCircle.r }; }

  get GasGridCircleTop() { return { x: this.GasGridCircle.cx, y: this.GasGridCircle.cy - this.GasGridCircle.r }; }
  get GasGridCircleBottom() { return { x: this.GasGridCircle.cx, y: this.GasGridCircle.cy - this.GasGridCircle.r }; }

  get HeatPumpBoxLeft() { return { x: this.HeatPumpBox.x, y: this.HeatPumpBox.y + this.HeatPumpBox.height / 2 }; }
  get HeatPumpBoxRight() { return { x: this.HeatPumpBox.x + this.HeatPumpBox.width, y: this.HeatPumpBox.y + this.HeatPumpBox.height / 2 }; }

  get CHPBoxLeft() { return { x: this.CHPBox1.x, y: this.CHPBox1.y + this.CHPBox1.height}; }
  get CHPBoxRight() { return { x: this.CHPBox1.x + this.CHPBox1.width, y: this.CHPBox1.y + this.CHPBox1.height}; }

  get BoilerBoxLeft() { return { x: this.BoilerBox.x, y: this.BoilerBox.y + this.BoilerBox.height / 2 }; }
  get BoilerBoxRight() { return { x: this.BoilerBox.x + this.BoilerBox.width, y: this.BoilerBox.y + this.BoilerBox.height / 2 }; }

  get OrCircleTop() { return { x: this.OrCircle.cx, y: this.OrCircle.cy - this.OrCircle.r }; }
  get OrCircleBottom() { return { x: this.OrCircle.cx, y: this.OrCircle.cy + this.OrCircle.r }; }
  get OrCircleLeft() { return { x: this.OrCircle.cx - this.OrCircle.r, y: this.OrCircle.cy }; }
  get OrCircleTopRight() { return {
    x: this.OrCircle.cx + this.OrCircle.r * Math.cos(-45 * Math.PI / 180),
    y: this.OrCircle.cy + this.OrCircle.r * Math.sin(-45 * Math.PI / 180)
  }; }
  get OrCircleBottomRight() { return {
    x: this.OrCircle.cx + this.OrCircle.r * Math.cos(-45 * Math.PI / 180),
    y: this.OrCircle.cy - this.OrCircle.r * Math.sin(-45 * Math.PI / 180)
  }; }

  get ThermalStorageBoxLeft() { return { x: this.ThermalStorageBox.x, y: this.ThermalStorageBox.y + this.ThermalStorageBox.height / 2 }; }
  get ThermalStorageBoxBottom() { return { x: this.ThermalStorageBox.x + this.ThermalStorageBox.width / 2, y: this.ThermalStorageBox.y + this.ThermalStorageBox.height }; }

  get HeatingNetworkCircleTop() { return { x: this.HeatingNetworkCircle.cx, y: this.HeatingNetworkCircle.cy - this.HeatingNetworkCircle.r }; }
  get HeatingNetworkCircleTopLeft() { return {
    x: this.HeatingNetworkCircle.cx - this.HeatingNetworkCircle.r * Math.cos(-45 * Math.PI / 180),
    y: this.HeatingNetworkCircle.cy + this.HeatingNetworkCircle.r * Math.sin(-45 * Math.PI / 180)
  }; }
   
}
