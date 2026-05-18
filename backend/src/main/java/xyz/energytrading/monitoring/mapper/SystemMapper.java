package xyz.energytrading.monitoring.mapper;

import org.mapstruct.Mapper;
import xyz.energytrading.monitoring.dto.SystemDTO;
import xyz.energytrading.monitoring.models.SystemEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SystemMapper {
    SystemDTO toDto(SystemEntity entity);
    SystemEntity toEntity(SystemDTO dto);
    List<SystemDTO> toDto(List<SystemEntity> entities);
    List<SystemEntity> toEntity(List<SystemDTO> dtos);
}
