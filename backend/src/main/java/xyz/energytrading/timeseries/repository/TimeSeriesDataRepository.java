package xyz.energytrading.timeseries.repository;

import org.springframework.data.repository.Repository;
import xyz.energytrading.timeseries.models.TimeSeriesData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TimeSeriesDataRepository extends Repository<TimeSeriesData, Long> {
    TimeSeriesData save(TimeSeriesData timeSeriesData);
    Optional<TimeSeriesData> findById(Long id);
    List<TimeSeriesData> findAll();
    List<TimeSeriesData> findAllByTag(String tag);
    List<TimeSeriesData> findAllByTagAndTimestampBetween(String tag, OffsetDateTime start, OffsetDateTime end);
    List<TimeSeriesData> findAllByTimestampBetween(OffsetDateTime start, OffsetDateTime end);
    void deleteById(Long id);
}
