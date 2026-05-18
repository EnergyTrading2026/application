package xyz.energytrading.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.energytrading.monitoring.models.SystemEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface SystemRepository extends JpaRepository<SystemEntity, Long> {
    Optional<SystemEntity> findTopByOrderByTimestampDesc();
    List<SystemEntity> findAllByTimestampBetweenOrderByTimestampDesc(OffsetDateTime start, OffsetDateTime end);
    List<SystemEntity> findAllByOrderByTimestampDesc();
}
