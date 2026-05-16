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

  //Gasgrid Begin ***********************************************************************************************************
  GasGridCircle = { cx: 150, cy: 550, r: 50 };

  showGasGridMenu = false;
  toggleGasGridMenu() { this.showGasGridMenu = !this.showGasGridMenu; }

  GasPrice: number = 35;
  showGasPricePopup = false;
  tempGasPrice: number = this.GasPrice;
  openGasPricePopup() { this.tempGasPrice = this.GasPrice; this.showGasPricePopup = true; }
  confirmGasPrice() { this.GasPrice = Number(this.tempGasPrice); this.showGasPricePopup = false; }
  cancelGasPrice() { this.showGasPricePopup = false; }

  CO2Factor: number = 0.201;
  showCO2FactorPopup = false;
  tempCO2Factor: number = this.GasPrice;
  openCO2FactorPopup() { this.tempCO2Factor = this.CO2Factor; this.showCO2FactorPopup = true; }
  confirmCO2Factor() { this.CO2Factor = Number(this.tempCO2Factor); this.showCO2FactorPopup = false; }
  cancelCO2Factor() { this.showCO2FactorPopup = false; }

  CO2Price: number = 60;
  showCO2PricePopup = false;
  tempCO2Price: number = this.CO2Price;
  openCO2PricePopup() { this.tempCO2Price = this.CO2Price; this.showCO2PricePopup = true; }
  confirmCO2Price() { this.CO2Price = Number(this.tempCO2Price); this.showCO2PricePopup = false; }
  cancelCO2Price() { this.showCO2PricePopup = false; }

  //Gasgrid End *********************************************************************************************************************


  //HeatPump Begin *******************************************************************************************************************
  HeatPumpOn: boolean = true;
  HeatPumpBox = { x: 300, y: 40, width: 115, height: 185 };
  onHeatPumpClick(): void {
  this.HeatPumpOn = !this.HeatPumpOn;
  }

  showHeatPumpMenu = false;
  toggleHeatPumpMenu() { this.showHeatPumpMenu = !this.showHeatPumpMenu; }

  RatedElectricalPower: number = 8;
  showRatedElectricalPowerPopup = false;
  tempRatedElectricalPower: number = this.RatedElectricalPower;
  openRatedElectricalPowerPopup() { this.tempRatedElectricalPower = this.RatedElectricalPower; this.showRatedElectricalPowerPopup = true; }
  confirmRatedElectricalPower() { this.RatedElectricalPower = Number(this.tempRatedElectricalPower); this.showRatedElectricalPowerPopup = false; }
  cancelRatedElectricalPower() { this.showRatedElectricalPowerPopup = false; }

  MinPower: number = 1;
  showMinPowerPopup = false;
  tempMinPower: number = this.MinPower;
  openMinPowerPopup() { this.tempMinPower = this.MinPower; this.showMinPowerPopup = true; }
  confirmMinPower() { this.MinPower = Number(this.tempMinPower); this.showMinPowerPopup = false; }
  cancelMinPower() { this.showMinPowerPopup = false; }

  COP: number = 3.5;
  showCOPPopup = false;
  tempCOP: number = this.GasPrice;
  openCOPPopup() { this.tempCOP = this.COP; this.showCOPPopup = true; }
  confirmCOP() { this.COP = Number(this.tempCOP); this.showCOPPopup = false; }
  cancelCOP() { this.showCOPPopup = false; }
  // HeatPump End ***********************************************************************************************************************


  //CHP Begin ****************************************************************************************************************************
  CHPOn: boolean = true;
  CHPBox1 = { x: 300, y: 320, width: 115, height: 92.5 };
  CHPBox2 = { x: 300, y: 320 + 92.5, width: 115, height: 92.5 };
  onCHPClick(): void {
  this.CHPOn = !this.CHPOn;
  }

  showCHPMenu = false;
  toggleCHPMenu() { this.showCHPMenu = !this.showCHPMenu; }

  CHPElectricalPowerMax: number = 6;
  showCHPElectricalPowerMaxPopup = false;
  tempCHPElectricalPowerMax: number = this.CHPElectricalPowerMax;
  openCHPElectricalPowerMaxPopup() { this.tempCHPElectricalPowerMax = this.CHPElectricalPowerMax; this.showCHPElectricalPowerMaxPopup = true; }
  confirmCHPElectricalPowerMax() { this.CHPElectricalPowerMax = Number(this.tempCHPElectricalPowerMax); this.showCHPElectricalPowerMaxPopup = false; }
  cancelCHPElectricalPowerMax() { this.showCHPElectricalPowerMaxPopup = false; }

  CHPElectricalPowerMin: number = 2;
  showCHPElectricalPowerMinPopup = false;
  tempCHPElectricalPowerMin: number = this.CHPElectricalPowerMin;
  openCHPElectricalPowerMinPopup() { this.tempCHPElectricalPowerMin = this.CHPElectricalPowerMin; this.showCHPElectricalPowerMinPopup = true; }
  confirmCHPElectricalPowerMin() { this.CHPElectricalPowerMin = Number(this.tempCHPElectricalPowerMin); this.showCHPElectricalPowerMinPopup = false; }
  cancelCHPElectricalPowerMin() { this.showCHPElectricalPowerMinPopup = false; }

  CHPThermalPowerMax: number = 7;
  showCHPThermalPowerMaxPopup = false;
  tempCHPThermalPowerMax: number = this.CHPThermalPowerMax;
  openCHPThermalPowerMaxPopup() { this.tempCHPThermalPowerMax = this.CHPThermalPowerMax; this.showCHPThermalPowerMaxPopup = true; }
  confirmCHPThermalPowerMax() { this.CHPThermalPowerMax = Number(this.tempCHPThermalPowerMax); this.showCHPThermalPowerMaxPopup = false; }
  cancelCHPThermalPowerMax() { this.showCHPThermalPowerMaxPopup = false; }

  CHPElectricalEfficiency: number = 0.4;
  showCHPElectricalEfficiencyPopup = false;
  tempCHPElectricalEfficiency: number = this.CHPElectricalEfficiency;
  openCHPElectricalEfficiencyPopup() { this.tempCHPElectricalEfficiency = this.CHPElectricalEfficiency; this.showCHPElectricalEfficiencyPopup = true; }
  confirmCHPElectricalEfficiency() { this.CHPElectricalEfficiency = Number(this.tempCHPElectricalEfficiency); this.showCHPElectricalEfficiencyPopup = false; }
  cancelCHPElectricalEfficiency() { this.showCHPElectricalEfficiencyPopup = false; }

  CHPThermalEfficiency: number = 0.48;
  showCHPThermalEfficiencyPopup = false;
  tempCHPThermalEfficiency: number = this.CHPThermalEfficiency;
  openCHPThermalEfficiencyPopup() { this.tempCHPThermalEfficiency = this.CHPThermalEfficiency; this.showCHPThermalEfficiencyPopup = true; }
  confirmCHPThermalEfficiency() { this.CHPThermalEfficiency = Number(this.tempCHPThermalEfficiency); this.showCHPThermalEfficiencyPopup = false; }
  cancelCHPThermalEfficiency() { this.showCHPThermalEfficiencyPopup = false; }

  CHPOverallEfficiency: number = 0.88;
  showCHPOverallEfficiencyPopup = false;
  tempCHPOverallEfficiency: number = this.CHPOverallEfficiency;
  openCHPOverallEfficiencyPopup() { this.tempCHPOverallEfficiency = this.CHPOverallEfficiency; this.showCHPOverallEfficiencyPopup = true; }
  confirmCHPOverallEfficiency() { this.CHPOverallEfficiency = Number(this.tempCHPOverallEfficiency); this.showCHPOverallEfficiencyPopup = false; }
  cancelCHPOverallEfficiency() { this.showCHPOverallEfficiencyPopup = false; }

  CHPMinRunTime: number = 8;
  showCHPMinRunTimePopup = false;
  tempCHPMinRunTime: number = this.CHPMinRunTime;
  openCHPMinRunTimePopup() { this.tempCHPMinRunTime = this.CHPMinRunTime; this.showCHPMinRunTimePopup = true; }
  confirmCHPMinRunTime() { this.CHPMinRunTime = Number(this.tempCHPMinRunTime); this.showCHPMinRunTimePopup = false; }
  cancelCHPMinRunTime() { this.showCHPMinRunTimePopup = false; }

  CHPMinDowntime: number = 8;
  showCHPMinDowntimePopup = false;
  tempCHPMinDowntime: number = this.CHPMinDowntime;
  openCHPMinDowntimePopup() { this.tempCHPMinDowntime = this.CHPMinDowntime; this.showCHPMinDowntimePopup = true; }
  confirmCHPMinDowntime() { this.CHPMinDowntime = Number(this.tempCHPMinDowntime); this.showCHPMinDowntimePopup = false; }
  cancelCHPMinDowntime() { this.showCHPMinDowntimePopup = false; }

  CHPStartupCosts: number = 600;
  showCHPStartupCostsPopup = false;
  tempCHPStartupCosts: number = this.CHPStartupCosts;
  openCHPStartupCostsPopup() { this.tempCHPStartupCosts = this.CHPStartupCosts; this.showCHPStartupCostsPopup = true; }
  confirmCHPStartupCosts() { this.CHPStartupCosts = Number(this.tempCHPStartupCosts); this.showCHPStartupCostsPopup = false; }
  cancelCHPStartupCosts() { this.showCHPStartupCostsPopup = false; }

  //CHP End *****************************************************************************************************************************


  //Boiler Begin ************************************************************************************************************************
  BoilerOn: boolean = true;
  BoilerBox = { x: 300, y: 600, width: 115, height: 185 };
  onBoilerClick(): void {
    this.BoilerOn = !this.BoilerOn;
  }

  showBoilerMenu = false;
  toggleBoilerMenu() { this.showBoilerMenu = !this.showBoilerMenu; }

  RatedThermalPower: number = 12;
  showRatedThermalPowerPopup = false;
  tempRatedThermalPower: number = this.RatedThermalPower;
  openRatedThermalPowerPopup() { this.tempRatedThermalPower = this.RatedThermalPower; this.showRatedThermalPowerPopup = true; }
  confirmRatedThermalPower() { this.RatedThermalPower = Number(this.tempRatedThermalPower); this.showRatedThermalPowerPopup = false; }
  cancelRatedThermalPower() { this.showRatedThermalPowerPopup = false; }

  BoilerMinLoad: number = 2;
  showBoilerMinLoadPopup = false;
  tempBoilerMinLoad: number = this.BoilerMinLoad;
  openBoilerMinLoadPopup() { this.tempBoilerMinLoad = this.BoilerMinLoad; this.showBoilerMinLoadPopup = true; }
  confirmBoilerMinLoad() { this.BoilerMinLoad = Number(this.tempBoilerMinLoad); this.showBoilerMinLoadPopup = false; }
  cancelBoilerMinLoad() { this.showBoilerMinLoadPopup = false; }

  BoilerEfficiency: number = 0.97;
  showBoilerEfficiencyPopup = false;
  tempBoilerEfficiency: number = this.BoilerEfficiency;
  openBoilerEfficiencyPopup() { this.tempBoilerEfficiency = this.BoilerEfficiency; this.showBoilerEfficiencyPopup = true; }
  confirmBoilerEfficiency() { this.BoilerEfficiency = Number(this.tempBoilerEfficiency); this.showBoilerEfficiencyPopup = false; }
  cancelBoilerEfficiency() { this.showBoilerEfficiencyPopup = false; }

  BoilerMinRunTime: number = 1;
  showBoilerMinRunTimePopup = false;
  tempBoilerMinRunTime: number = this.BoilerMinRunTime;
  openBoilerMinRunTimePopup() { this.tempBoilerMinRunTime = this.BoilerMinRunTime; this.showBoilerMinRunTimePopup = true; }
  confirmBoilerMinRunTime() { this.BoilerMinRunTime = Number(this.tempBoilerMinRunTime); this.showBoilerMinRunTimePopup = false; }
  cancelBoilerMinRunTime() { this.showBoilerMinRunTimePopup = false; }

  BoilerMinDowntime: number = 1;
  showBoilerMinDowntimePopup = false;
  tempBoilerMinDowntime: number = this.BoilerMinDowntime;
  openBoilerMinDowntimePopup() { this.tempBoilerMinDowntime = this.BoilerMinDowntime; this.showBoilerMinDowntimePopup = true; }
  confirmBoilerMinDowntime() { this.BoilerMinDowntime = Number(this.tempBoilerMinDowntime); this.showBoilerMinDowntimePopup = false; }
  cancelBoilerMinDowntime() { this.showBoilerMinDowntimePopup = false; }

  //Boiler End *************************************************************************************************************************

  OrCircle = { cx: 500, cy: 320 + 92.5, r: 20 };

  // Thermal Storage Begin **********************************************************************************************************
  ThermalStorageOn: boolean = true;
  ThermalStorageBox = { x: 650, y: 180, width: 115, height: 185 };
  onThermalStorageClick(): void {
    this.ThermalStorageOn = !this.ThermalStorageOn;
  }

  showThermalStorageMenu = false;
  toggleThermalStorageMenu() { this.showThermalStorageMenu = !this.showThermalStorageMenu; }

  StorageCapacity: number = 200;
  showStorageCapacityPopup = false;
  tempStorageCapacity: number = this.StorageCapacity;
  openStorageCapacityPopup() { this.tempStorageCapacity = this.StorageCapacity; this.showStorageCapacityPopup = true; }
  confirmStorageCapacity() { this.StorageCapacity = Number(this.tempStorageCapacity); this.showStorageCapacityPopup = false; }
  cancelStorageCapacity() { this.showStorageCapacityPopup = false; }

  MaxChargingPower: number = 15;
  showMaxChargingPowerPopup = false;
  tempMaxChargingPower: number = this.MaxChargingPower;
  openMaxChargingPowerPopup() { this.tempMaxChargingPower = this.MaxChargingPower; this.showMaxChargingPowerPopup = true; }
  confirmMaxChargingPower() { this.MaxChargingPower = Number(this.tempMaxChargingPower); this.showMaxChargingPowerPopup = false; }
  cancelMaxChargingPower() { this.showMaxChargingPowerPopup = false; }

  MaxDischargingPower: number = 15;
  showMaxDischargingPowerPopup = false;
  tempMaxDischargingPower: number = this.MaxDischargingPower;
  openMaxDischargingPowerPopup() { this.tempMaxDischargingPower = this.MaxDischargingPower; this.showMaxDischargingPowerPopup = true; }
  confirmMaxDischargingPower() { this.MaxDischargingPower = Number(this.tempMaxDischargingPower); this.showMaxDischargingPowerPopup = false; }
  cancelMaxDischargingPower() { this.showMaxDischargingPowerPopup = false; }

  StorageLoss: number = 0.000125;
  showStorageLossPopup = false;
  tempStorageLoss: number = this.StorageLoss;
  openStorageLossPopup() { this.tempStorageLoss = this.StorageLoss; this.showStorageLossPopup = true; }
  confirmStorageLoss() { this.StorageLoss = Number(this.tempStorageLoss); this.showStorageLossPopup = false; }
  cancelStorageLoss() { this.showStorageLossPopup = false; }

  InitialSoC: number = 200;
  showInitialSoCPopup = false;
  tempInitialSoC: number = this.InitialSoC;
  openInitialSoCPopup() { this.tempInitialSoC = this.InitialSoC; this.showInitialSoCPopup = true; }
  confirmInitialSoC() { this.InitialSoC = Number(this.tempInitialSoC); this.showInitialSoCPopup = false; }
  cancelInitialSoC() { this.showInitialSoCPopup = false; }
  // Thermal Storage End ***************************************************************************************************************
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
