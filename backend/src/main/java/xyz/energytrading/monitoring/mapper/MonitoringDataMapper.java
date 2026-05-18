package xyz.energytrading.monitoring.mapper;

import org.mapstruct.Mapper;
import xyz.energytrading.monitoring.dto.MonitoringDataDTO;

import java.util.List;

/**
 * Mapper for the complete MonitoringData structure.
 * Composes system, assets, and storage mappers.
 */
@Mapper(componentModel = "spring", uses = {SystemMapper.class, AssetMapper.class, StorageMapper.class})
public interface MonitoringDataMapper {
    MonitoringDataDTO toDto(
        xyz.energytrading.monitoring.models.SystemEntity system,
        List<xyz.energytrading.monitoring.models.AssetEntity> assets,
        xyz.energytrading.monitoring.models.StorageEntity storage
    );
}
