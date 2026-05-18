package xyz.energytrading.monitoring.dto;

import java.time.OffsetDateTime;

public class SystemDTO {

    private Long id;
    private Double heatDemand;
    private Double electricityPrice;
    private Double gasPrice;
    private OffsetDateTime timestamp;

    public SystemDTO() {
    }

    public SystemDTO(Double heatDemand, Double electricityPrice, Double gasPrice, OffsetDateTime timestamp) {
        this.heatDemand = heatDemand;
        this.electricityPrice = electricityPrice;
        this.gasPrice = gasPrice;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeatDemand() {
        return heatDemand;
    }

    public void setHeatDemand(Double heatDemand) {
        this.heatDemand = heatDemand;
    }

    public Double getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(Double gasPrice) {
        this.gasPrice = gasPrice;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
