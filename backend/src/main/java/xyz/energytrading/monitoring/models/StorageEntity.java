package xyz.energytrading.monitoring.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "monitoring_storage")
public class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "percent", nullable = false)
    private Double percent;

    @Column(name = "capacity", nullable = false)
    private Double capacity;

    @Column(name = "charging", nullable = false)
    private Double charging;

    @Column(name = "discharge", nullable = false)
    private Double discharge;

    @Column(name = "net_balance", nullable = false)
    private String netBalance;

    @Column(name = "range", nullable = false)
    private String range;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MetricEntity> metrics = new ArrayList<>();

    public StorageEntity() {
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

    public List<MetricEntity> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricEntity> metrics) {
        this.metrics = metrics;
    }

    public void addMetric(MetricEntity metric) {
        metric.setStorage(this);
        this.metrics.add(metric);
    }

    public void removeMetric(MetricEntity metric) {
        this.metrics.remove(metric);
        metric.setStorage(null);
    }
}
