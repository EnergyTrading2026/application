package xyz.energytrading.timeseries.dto;

import xyz.energytrading.timeseries.models.ModelTag;
import xyz.energytrading.timeseries.models.Unit;

import java.time.OffsetDateTime;

public class TimeSeriesDataDTO {

    private Long id;
    private ModelTag tag;
    private double value;
    private Unit unit;
    private OffsetDateTime timestamp;

    public TimeSeriesDataDTO() {}

    public TimeSeriesDataDTO(Long id, ModelTag tag, double value, Unit unit, OffsetDateTime timestamp) {
        this.id = id;
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
