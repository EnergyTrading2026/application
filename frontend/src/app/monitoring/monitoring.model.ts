export type AssetType = 'heatPump' | 'chp' | 'boiler';

export interface Asset {
  id: number;
  name: string;
  type: AssetType;
  icon: string;
  active: boolean;
  thermalOutput: number;
  thermalOutputUnit: string;
  networkOutput: number;
  input: number;
  inputType: string;
  inputUnit: string;
  inputCost: number;
}

export interface CHP extends Asset {
  type: 'chp';
  electricalOutput: number;
  electricalOutputUnit: string;
  electricalOutputRevenue: number;
}

export interface Metric {
  label: string;
  value: string;
}

export interface Storage {
  title: string;
  capacity: number;
  percent: number;
  charging: number;
  discharge: number;
  metrics: Metric[];
  netBalance: string;
  range: string;
}

export interface System {
  heatDemand: number;
  electricityPrice: number;
  gasPrice: number;
}

export interface MonitoringData {
  system: System;
  assets: Asset[];
  storage: Storage;
}
