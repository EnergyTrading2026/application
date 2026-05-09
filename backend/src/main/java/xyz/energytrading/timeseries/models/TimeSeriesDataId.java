package xyz.energytrading.timeseries.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class TimeSeriesDataId implements Serializable {
    @Column(name = "timestamp")
    private OffsetDateTime timestamp;
    @Enumerated(EnumType.STRING)
    @Column(name = "model_identifier")
    private ModelIdentifier modelIdentifier;

    public TimeSeriesDataId() {}

    public TimeSeriesDataId(OffsetDateTime timestamp, ModelIdentifier modelIdentifier) {
        this.timestamp = timestamp;
        this.modelIdentifier = modelIdentifier;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ModelIdentifier getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(ModelIdentifier modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeriesDataId that = (TimeSeriesDataId) o;
        return Objects.equals(timestamp, that.timestamp) && modelIdentifier == that.modelIdentifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, modelIdentifier);
    }
}
