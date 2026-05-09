package xyz.energytrading.timeseries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.energytrading.timeseries.models.TimeSeriesData;
import xyz.energytrading.timeseries.models.TimeSeriesDataId;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TimeSeriesDataRepository extends JpaRepository<TimeSeriesData, TimeSeriesDataId> {
    List<TimeSeriesData> findAllByTimestampBetween(OffsetDateTime start, OffsetDateTime end);
    Optional<TimeSeriesData> findById(Long id);
    void deleteById(Long id);
}
