package xyz.energytrading.monitoring.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "monitoring_asset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "asset_id", nullable = false)
    private Integer assetId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "thermal_output", nullable = false)
    private Double thermalOutput;

    @Column(name = "thermal_output_unit", nullable = false)
    private String thermalOutputUnit;

    @Column(name = "network_output", nullable = false)
    private Double networkOutput;

    @Column(name = "input", nullable = false)
    private Double input;

    @Column(name = "input_type", nullable = false)
    private String inputType;

    @Column(name = "input_unit", nullable = false)
    private String inputUnit;

    @Column(name = "input_cost", nullable = false)
    private Double inputCost;

    @Column(name = "electrical_output")
    private Double electricalOutput;

    @Column(name = "electrical_output_unit")
    private String electricalOutputUnit;

    @Column(name = "electrical_output_revenue")
    private Double electricalOutputRevenue;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    public AssetEntity() {
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
