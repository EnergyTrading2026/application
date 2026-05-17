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
    @Enumerated(EnumType.STRING)
    @Column(name = "tsd_source")
    private TSDSource tsdSource;
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

    public TSDSource getTsdSource() {
        return tsdSource;
    }

    public void setTsdSource(TSDSource tsdSource) {
        this.tsdSource = tsdSource;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeriesDataId that = (TimeSeriesDataId) o;
        return Objects.equals(timestamp, that.timestamp) && modelIdentifier == that.modelIdentifier && tsdSource == that.tsdSource;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, modelIdentifier, tsdSource);
    }
}
