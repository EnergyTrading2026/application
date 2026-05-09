package xyz.energytrading.timeseries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.mapper.TimeSeriesDataMapper;
import xyz.energytrading.timeseries.models.TimeSeriesData;
import xyz.energytrading.timeseries.repository.TimeSeriesDataRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TimeSeriesDataService {

    @Autowired
    private TimeSeriesDataRepository timeSeriesDataRepository;

    @Autowired
    private TimeSeriesDataMapper timeSeriesDataMapper;

    public TimeSeriesDataService() {}

    public TimeSeriesDataDTO create(TimeSeriesDataDTO timeSeriesDataDTO) {
        TimeSeriesData entity = timeSeriesDataMapper.toEntity(timeSeriesDataDTO);
        TimeSeriesData saved = timeSeriesDataRepository.save(entity);
        return timeSeriesDataMapper.toDto(saved);
    }

    public List<TimeSeriesDataDTO> getAll() {
        List<TimeSeriesData> all = timeSeriesDataRepository.findAll();
        return timeSeriesDataMapper.toDto(all);
    }

    public TimeSeriesDataDTO getById(Long id) {
        return timeSeriesDataRepository.findById(id)
                .map(timeSeriesDataMapper::toDto)
                .orElse(null);
    }

    public List<TimeSeriesDataDTO> getAllByTag(String tag) {
        List<TimeSeriesData> byTag = timeSeriesDataRepository.findAllByTag(tag);
        return timeSeriesDataMapper.toDto(byTag);
    }

    public List<TimeSeriesDataDTO> getAllByDateRange(OffsetDateTime start, OffsetDateTime end) {
        List<TimeSeriesData> byDate = timeSeriesDataRepository.findAllByTimestampBetween(start, end);
        return timeSeriesDataMapper.toDto(byDate);
    }

    public List<TimeSeriesDataDTO> getAllByTagAndDateRange(String tag, OffsetDateTime start, OffsetDateTime end) {
        List<TimeSeriesData> byTagAndDate = timeSeriesDataRepository.findAllByTagAndTimestampBetween(tag, start, end);
        return timeSeriesDataMapper.toDto(byTagAndDate);
    }

    public void deleteById(Long id) {
        timeSeriesDataRepository.deleteById(id);
    }
}
