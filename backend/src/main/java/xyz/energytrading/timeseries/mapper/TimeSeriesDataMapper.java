package xyz.energytrading.timeseries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.models.TimeSeriesData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSeriesDataMapper {
    @Mapping(target = "id.timestamp", source = "timestamp")
    @Mapping(target = "id.modelIdentifier", source = "modelIdentifier")
    @Mapping(target = "id.tsdSource", source = "tsdSource")
    TimeSeriesData toEntity(TimeSeriesDataDTO dto);
    
    @Mapping(target = "timestamp", source = "id.timestamp")
    @Mapping(target = "modelIdentifier", source = "id.modelIdentifier")
    @Mapping(target = "tsdSource", source = "id.tsdSource")
    TimeSeriesDataDTO toDto(TimeSeriesData entity);
    
    List<TimeSeriesDataDTO> toDto(List<TimeSeriesData> entities);
    List<TimeSeriesData> toEntity(List<TimeSeriesDataDTO> dtos);
}
