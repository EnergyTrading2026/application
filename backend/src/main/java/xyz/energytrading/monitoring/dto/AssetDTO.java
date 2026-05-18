package xyz.energytrading.monitoring.dto;

import java.time.OffsetDateTime;

public class AssetDTO {

    private Long id;
    private Integer assetId;
    private String name;
    private String type;
    private String icon;
    private Boolean active;
    private Double thermalOutput;
    private String thermalOutputUnit;
    private Double networkOutput;
    private Double input;
    private String inputType;
    private String inputUnit;
    private Double inputCost;
    private Double electricalOutput;
    private String electricalOutputUnit;
    private Double electricalOutputRevenue;
    private OffsetDateTime timestamp;

    public AssetDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getThermalOutput() {
        return thermalOutput;
    }

    public void setThermalOutput(Double thermalOutput) {
        this.thermalOutput = thermalOutput;
    }

    public String getThermalOutputUnit() {
        return thermalOutputUnit;
    }

    public void setThermalOutputUnit(String thermalOutputUnit) {
        this.thermalOutputUnit = thermalOutputUnit;
    }

    public Double getNetworkOutput() {
        return networkOutput;
    }

    public void setNetworkOutput(Double networkOutput) {
        this.networkOutput = networkOutput;
    }

    public Double getInput() {
        return input;
    }

    public void setInput(Double input) {
        this.input = input;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getInputUnit() {
        return inputUnit;
    }

    public void setInputUnit(String inputUnit) {
        this.inputUnit = inputUnit;
    }

    public Double getInputCost() {
        return inputCost;
    }

    public void setInputCost(Double inputCost) {
        this.inputCost = inputCost;
    }

    public Double getElectricalOutput() {
        return electricalOutput;
    }

    public void setElectricalOutput(Double electricalOutput) {
        this.electricalOutput = electricalOutput;
    }

    public String getElectricalOutputUnit() {
        return electricalOutputUnit;
    }

    public void setElectricalOutputUnit(String electricalOutputUnit) {
        this.electricalOutputUnit = electricalOutputUnit;
    }

    public Double getElectricalOutputRevenue() {
        return electricalOutputRevenue;
    }

    public void setElectricalOutputRevenue(Double electricalOutputRevenue) {
        this.electricalOutputRevenue = electricalOutputRevenue;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
