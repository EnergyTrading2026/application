package xyz.energytrading.timeseries.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class TimeSeriesDataId implements Serializable {
    private Long id;
    private OffsetDateTime timestamp;

    public TimeSeriesDataId() {}

    public TimeSeriesDataId(Long id, OffsetDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeriesDataId that = (TimeSeriesDataId) o;
        return Objects.equals(id, that.id) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp);
    }
}
