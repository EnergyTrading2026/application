package xyz.energytrading.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.energytrading.monitoring.models.AssetEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
    List<AssetEntity> findAllByOrderByTimestampDesc();
    List<AssetEntity> findAllByTimestampBetweenOrderByTimestampDesc(OffsetDateTime start, OffsetDateTime end);
    List<AssetEntity> findAllByTimestamp(OffsetDateTime timestamp);
}
