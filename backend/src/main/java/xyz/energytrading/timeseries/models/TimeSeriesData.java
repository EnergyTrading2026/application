package xyz.energytrading.timeseries.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "time_series_data")
public class TimeSeriesData {

    @EmbeddedId
    private TimeSeriesDataId id;

    @Column(name = "value", nullable = false)
    private double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private Unit unit;

    public TimeSeriesData() {
        this.id = new TimeSeriesDataId();
    }

    public TimeSeriesData(double value, Unit unit, ModelIdentifier modelIdentifier, OffsetDateTime timestamp) {
        this.id = new TimeSeriesDataId(timestamp, modelIdentifier);
        this.value = value;
        this.unit = unit;
    }

    public TimeSeriesDataId getId() {
        return id;
    }

    public void setId(TimeSeriesDataId id) {
        this.id = id;
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

    @Transient
    public OffsetDateTime getTimestamp() {
        return id != null ? id.getTimestamp() : null;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        if (this.id == null) {
            this.id = new TimeSeriesDataId();
        }
        this.id.setTimestamp(timestamp);
    }

    @Transient
    public ModelIdentifier getModelIdentifier() {
        return id != null ? id.getModelIdentifier() : null;
    }

    public void setModelIdentifier(ModelIdentifier modelIdentifier) {
        if (this.id == null) {
            this.id = new TimeSeriesDataId();
        }
        this.id.setModelIdentifier(modelIdentifier);
    }
}
