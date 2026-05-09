package xyz.energytrading.timeseries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.models.TimeSeriesData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSeriesDataMapper {
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "id.timestamp", source = "timestamp")
    TimeSeriesData toEntity(TimeSeriesDataDTO dto);
    
    @Mapping(target = "id", source = "id.id")
    @Mapping(target = "timestamp", source = "id.timestamp")
    TimeSeriesDataDTO toDto(TimeSeriesData entity);
    
    List<TimeSeriesDataDTO> toDto(List<TimeSeriesData> entities);
    List<TimeSeriesData> toEntity(List<TimeSeriesDataDTO> dtos);
}
