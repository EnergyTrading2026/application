package xyz.energytrading.timeseries.dto;

import xyz.energytrading.timeseries.models.ModelIdentifier;
import xyz.energytrading.timeseries.models.Unit;

import java.time.OffsetDateTime;

public class TimeSeriesDataDTO {

    private Long id;
    private double value;
    private Unit unit;
    private ModelIdentifier modelIdentifier;
    private OffsetDateTime timestamp;

    public TimeSeriesDataDTO() {}

    public TimeSeriesDataDTO(Long id, double value, Unit unit, ModelIdentifier modelIdentifier, OffsetDateTime timestamp) {
        this.id = id;
        this.value = value;
        this.unit = unit;
        this.modelIdentifier = modelIdentifier;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
