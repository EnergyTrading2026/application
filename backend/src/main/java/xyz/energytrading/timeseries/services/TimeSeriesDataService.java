package xyz.energytrading.timeseries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.mapper.TimeSeriesDataMapper;
import xyz.energytrading.timeseries.models.TimeSeriesData;
import xyz.energytrading.timeseries.models.TimeSeriesDataId;
import xyz.energytrading.timeseries.repository.TimeSeriesDataRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TimeSeriesDataService {

    @Autowired
    private TimeSeriesDataRepository timeSeriesDataRepository;

    @Autowired
    private TimeSeriesDataMapper timeSeriesDataMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TimeSeriesDataService() {}

    public TimeSeriesDataDTO create(TimeSeriesDataDTO timeSeriesDataDTO) {
        // Get next ID from sequence
        Long nextId = jdbcTemplate.queryForObject("SELECT nextval('time_series_data_id_seq')", Long.class);
        
        TimeSeriesData entity = timeSeriesDataMapper.toEntity(timeSeriesDataDTO);
        // Set the generated ID and timestamp
        TimeSeriesDataId id = new TimeSeriesDataId(nextId, entity.getTimestamp());
        entity.setId(id);
        
        TimeSeriesData saved = timeSeriesDataRepository.save(entity);
        return timeSeriesDataMapper.toDto(saved);
    }

    public List<TimeSeriesDataDTO> getAll() {
        List<TimeSeriesData> all = timeSeriesDataRepository.findAll();
        return timeSeriesDataMapper.toDto(all);
    }

    public TimeSeriesDataDTO getById(Long id) {
        List<TimeSeriesData> all = timeSeriesDataRepository.findAll();
        return all.stream()
                .filter(e -> e.getId() != null && e.getId().getId() != null && e.getId().getId().equals(id))
                .findFirst()
                .map(timeSeriesDataMapper::toDto)
                .orElse(null);
    }

    public List<TimeSeriesDataDTO> getAllByDateRange(OffsetDateTime start, OffsetDateTime end) {
        List<TimeSeriesData> byDate = timeSeriesDataRepository.findAllByTimestampBetween(start, end);
        return timeSeriesDataMapper.toDto(byDate);
    }

    public void deleteById(Long id) {
        List<TimeSeriesData> all = timeSeriesDataRepository.findAll();
        all.stream()
                .filter(e -> e.getId() != null && e.getId().getId() != null && e.getId().getId().equals(id))
                .findFirst()
                .ifPresent(e -> timeSeriesDataRepository.deleteById(e.getId()));
    }
}
