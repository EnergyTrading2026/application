package xyz.energytrading.timeseries.mapper;

import org.mapstruct.Mapper;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.models.TimeSeriesData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSeriesDataMapper {
    TimeSeriesDataDTO toDto(TimeSeriesData entity);
    TimeSeriesData toEntity(TimeSeriesDataDTO dto);
    List<TimeSeriesDataDTO> toDto(List<TimeSeriesData> entities);
    List<TimeSeriesData> toEntity(List<TimeSeriesDataDTO> dtos);
}
