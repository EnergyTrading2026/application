package xyz.energytrading.monitoring.dto;

import java.time.OffsetDateTime;

public class MetricDTO {

    private Long id;
    private Long storageId;
    private String label;
    private String value;
    private OffsetDateTime timestamp;

    public MetricDTO() {
    }

    public MetricDTO(String label, String value, OffsetDateTime timestamp) {
        this.label = label;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
