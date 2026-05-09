package xyz.energytrading.timeseries.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "time_series_data")
public class TimeSeriesData {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "timestamp", column = @Column(name = "timestamp"))
    })
    private TimeSeriesDataId id;

    @Column(name = "value", nullable = false)
    private double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private Unit unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_identifier", nullable = false)
    private ModelIdentifier modelIdentifier;

    public TimeSeriesData() {
        this.id = new TimeSeriesDataId();
    }

    public TimeSeriesData(double value, Unit unit, ModelIdentifier modelIdentifier, OffsetDateTime timestamp) {
        this.id = new TimeSeriesDataId(null, timestamp);
        this.value = value;
        this.unit = unit;
        this.modelIdentifier = modelIdentifier;
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

    public ModelIdentifier getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(ModelIdentifier modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    @Transient
    public Long getSimpleId() {
        return id != null ? id.getId() : null;
    }

    public void setSimpleId(Long simpleId) {
        if (this.id == null) {
            this.id = new TimeSeriesDataId();
        }
        this.id.setId(simpleId);
    }

    public OffsetDateTime getTimestamp() {
        return id != null ? id.getTimestamp() : null;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        if (this.id == null) {
            this.id = new TimeSeriesDataId();
        }
        this.id.setTimestamp(timestamp);
    }
}
