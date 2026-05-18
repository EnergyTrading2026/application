package xyz.energytrading.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.energytrading.monitoring.models.StorageEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<StorageEntity, Long> {
    Optional<StorageEntity> findTopByOrderByTimestampDesc();
    List<StorageEntity> findAllByOrderByTimestampDesc();
    List<StorageEntity> findAllByTimestampBetweenOrderByTimestampDesc(OffsetDateTime start, OffsetDateTime end);
}
