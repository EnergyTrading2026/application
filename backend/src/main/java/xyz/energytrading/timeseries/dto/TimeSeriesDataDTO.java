package xyz.energytrading.timeseries.dto;

import xyz.energytrading.timeseries.models.ModelIdentifier;
import xyz.energytrading.timeseries.models.TSDSource;
import xyz.energytrading.timeseries.models.Unit;

import java.time.OffsetDateTime;

public class TimeSeriesDataDTO {

    private double value;
    private Unit unit;
    private ModelIdentifier modelIdentifier;
    private OffsetDateTime timestamp;
    private TSDSource tsdSource;

    public TimeSeriesDataDTO() {}

    public TimeSeriesDataDTO(double value, Unit unit, ModelIdentifier modelIdentifier, OffsetDateTime timestamp) {
        this.value = value;
        this.unit = unit;
        this.modelIdentifier = modelIdentifier;
        this.timestamp = timestamp;
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

    public TSDSource getTsdSource() {
        return tsdSource;
    }

    public void setTsdSource(TSDSource tsdSource) {
        this.tsdSource = tsdSource;
    }
}
