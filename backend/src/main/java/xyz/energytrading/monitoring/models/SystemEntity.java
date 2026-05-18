package xyz.energytrading.monitoring.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "monitoring_system")
public class SystemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "heat_demand", nullable = false)
    private Double heatDemand;

    @Column(name = "electricity_price", nullable = false)
    private Double electricityPrice;

    @Column(name = "gas_price", nullable = false)
    private Double gasPrice;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    public SystemEntity() {
    }

    public SystemEntity(Double heatDemand, Double electricityPrice, Double gasPrice, OffsetDateTime timestamp) {
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
