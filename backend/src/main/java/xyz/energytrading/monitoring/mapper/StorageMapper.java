package xyz.energytrading.monitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.energytrading.monitoring.dto.StorageDTO;
import xyz.energytrading.monitoring.models.StorageEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MetricMapper.class})
public interface StorageMapper {
    @Mapping(target = "metrics", source = "metrics")
    StorageDTO toDto(StorageEntity entity);
    
    @Mapping(target = "metrics", source = "metrics")
    StorageEntity toEntity(StorageDTO dto);
    
    List<StorageDTO> toDto(List<StorageEntity> entities);
    List<StorageEntity> toEntity(List<StorageDTO> dtos);
}
