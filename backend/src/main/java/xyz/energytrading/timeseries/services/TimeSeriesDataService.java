package xyz.energytrading.timeseries.services;

import org.springframework.stereotype.Service;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.mapper.TimeSeriesDataMapper;
import xyz.energytrading.timeseries.models.ModelIdentifier;
import xyz.energytrading.timeseries.models.TimeSeriesData;
import xyz.energytrading.timeseries.models.TimeSeriesDataId;
import xyz.energytrading.timeseries.repository.TimeSeriesDataRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSeriesDataService {

    private final TimeSeriesDataRepository timeSeriesDataRepository;
    private final TimeSeriesDataMapper timeSeriesDataMapper;

    public TimeSeriesDataService(
            TimeSeriesDataRepository timeSeriesDataRepository,
            TimeSeriesDataMapper timeSeriesDataMapper) {
        this.timeSeriesDataRepository = timeSeriesDataRepository;
        this.timeSeriesDataMapper = timeSeriesDataMapper;
    }

    public TimeSeriesDataDTO create(TimeSeriesDataDTO timeSeriesDataDTO) {
        TimeSeriesData entity = timeSeriesDataMapper.toEntity(timeSeriesDataDTO);
        TimeSeriesData saved = timeSeriesDataRepository.save(entity);
        return timeSeriesDataMapper.toDto(saved);
    }

    public List<TimeSeriesDataDTO> getAll() {
        List<TimeSeriesData> all = timeSeriesDataRepository.findAll();
        return timeSeriesDataMapper.toDto(all);
    }

    public TimeSeriesDataDTO getById(OffsetDateTime timestamp, String modelIdentifier) {
        TimeSeriesDataId id = new TimeSeriesDataId(timestamp, ModelIdentifier.valueOf(modelIdentifier));
        Optional<TimeSeriesData> result = timeSeriesDataRepository.findById(id);
        return result.map(timeSeriesDataMapper::toDto).orElse(null);
    }

    public List<TimeSeriesDataDTO> getAllByDateRange(OffsetDateTime start, OffsetDateTime end) {
        List<TimeSeriesData> byDate = timeSeriesDataRepository.findAllByTimestampBetween(start, end);
        return timeSeriesDataMapper.toDto(byDate);
    }

    public void deleteById(OffsetDateTime timestamp, String modelIdentifier) {
        TimeSeriesDataId id = new TimeSeriesDataId(timestamp, ModelIdentifier.valueOf(modelIdentifier));
        timeSeriesDataRepository.deleteById(id);
    }
}
