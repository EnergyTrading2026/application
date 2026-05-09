package xyz.energytrading.timeseries.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "time_series_data")
public class TimeSeriesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private ModelTag tag;

    @Column(name = "value", nullable = false)
    private double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private Unit unit;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    public TimeSeriesData() {}

    public TimeSeriesData(ModelTag tag, double value, Unit unit, OffsetDateTime timestamp) {
        this.tag = tag;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelTag getTag() {
        return tag;
    }

    public void setTag(ModelTag tag) {
        this.tag = tag;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
