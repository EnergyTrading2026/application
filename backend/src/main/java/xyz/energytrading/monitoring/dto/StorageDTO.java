package xyz.energytrading.monitoring.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class StorageDTO {

    private Long id;
    private String title;
    private Double percent;
    private Double capacity;
    private Double charging;
    private Double discharge;
    private String netBalance;
    private String range;
    private OffsetDateTime timestamp;
    private List<MetricDTO> metrics = new ArrayList<>();

    public StorageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getCharging() {
        return charging;
    }

    public void setCharging(Double charging) {
        this.charging = charging;
    }

    public Double getDischarge() {
        return discharge;
    }

    public void setDischarge(Double discharge) {
        this.discharge = discharge;
    }

    public String getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(String netBalance) {
        this.netBalance = netBalance;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<MetricDTO> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDTO> metrics) {
        this.metrics = metrics;
    }
}
