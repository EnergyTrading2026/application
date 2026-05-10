import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-simulation',
  imports: [CommonModule, FormsModule],
  templateUrl: './simulation.html',
  styleUrl: './simulation.css',
})
export class Simulation {
  //Powergrid begin
  PowerGridCircle = { cx: 150, cy: 250, r: 50 };
  //Powergrid end

  //Gasgrid and everything related Begin
  GasGridCircle = { cx: 150, cy: 550, r: 50 };

  showGasGridMenu = false;
  toggleGasGridMenu() { this.showGasGridMenu = !this.showGasGridMenu; }
  GasPrice: number = 35;
  showGasPricePopup = false;
  tempGasPrice: number = this.GasPrice;

  openGasPricePopup() {
    this.tempGasPrice = this.GasPrice; // reset to current value
    this.showGasPricePopup = true;
  }

  confirmGasPrice() {
    this.GasPrice = Number(this.tempGasPrice);
    this.showGasPricePopup = false;
  }

  cancelGasPrice() {
    this.showGasPricePopup = false;
  }

  CO2Factor: number = 0.201;
  showCO2FactorPopup = false;
  tempCO2Factor: number = this.GasPrice;

  openCO2FactorPopup() {
    this.tempCO2Factor = this.CO2Factor; // reset to current value
    this.showCO2FactorPopup = true;
  }

  confirmCO2Factor() {
    this.CO2Factor = Number(this.tempCO2Factor);
    this.showCO2FactorPopup = false;
  }

  cancelCO2Factor() {
    this.showCO2FactorPopup = false;
  }

  CO2Price: number = 60;
  showCO2PricePopup = false;
  tempCO2Price: number = this.CO2Price;

  openCO2PricePopup() {
    this.tempCO2Price = this.CO2Price; // reset to current value
    this.showCO2PricePopup = true;
  }

  confirmCO2Price() {
    this.CO2Price = Number(this.tempCO2Price);
    this.showCO2PricePopup = false;
  }

  cancelCO2Price() {
    this.showCO2PricePopup = false;
  }

  //Gasgrid and everything related End


  HeatPumpOn: boolean = true;
  HeatPumpBox = { x: 300, y: 40, width: 115, height: 185 };
  onHeatPumpClick(): void {
  this.HeatPumpOn = !this.HeatPumpOn;
  }

  CHPOn: boolean = true;
  CHPBox1 = { x: 300, y: 320, width: 115, height: 92.5 };
  CHPBox2 = { x: 300, y: 320 + 92.5, width: 115, height: 92.5 };
  onCHPClick(): void {
  this.CHPOn = !this.CHPOn;
  }
  BoilerOn: boolean = true;
  BoilerBox = { x: 300, y: 600, width: 115, height: 185 };
  onBoilerClick(): void {
    this.BoilerOn = !this.BoilerOn;
  }

  OrCircle = { cx: 500, cy: 320 + 92.5, r: 20 };

  ThermalStorageOn: boolean = true;
  ThermalStorageBox = { x: 650, y: 180, width: 115, height: 185 };
  onThermalStorageClick(): void {
    this.ThermalStorageOn = !this.ThermalStorageOn;
  }
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
