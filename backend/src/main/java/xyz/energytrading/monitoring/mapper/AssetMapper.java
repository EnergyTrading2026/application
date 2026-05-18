package xyz.energytrading.monitoring.mapper;

import org.mapstruct.Mapper;
import xyz.energytrading.monitoring.dto.AssetDTO;
import xyz.energytrading.monitoring.models.AssetEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetDTO toDto(AssetEntity entity);
    AssetEntity toEntity(AssetDTO dto);
    List<AssetDTO> toDto(List<AssetEntity> entities);
    List<AssetEntity> toEntity(List<AssetDTO> dtos);
}
