package xyz.energytrading.monitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.energytrading.monitoring.dto.MetricDTO;
import xyz.energytrading.monitoring.models.MetricEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetricMapper {
    @Mapping(target = "storageId", source = "storage.id")
    MetricDTO toDto(MetricEntity entity);
    
    @Mapping(target = "storage.id", source = "storageId")
    MetricEntity toEntity(MetricDTO dto);
    
    List<MetricDTO> toDto(List<MetricEntity> entities);
    List<MetricEntity> toEntity(List<MetricDTO> dtos);
}
