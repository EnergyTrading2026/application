package xyz.energytrading.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.energytrading.monitoring.models.MetricEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface MetricRepository extends JpaRepository<MetricEntity, Long> {
    List<MetricEntity> findAllByStorageId(Long storageId);
    List<MetricEntity> findAllByStorageIdAndTimestampOrderByTimestampDesc(Long storageId, OffsetDateTime timestamp);
}
