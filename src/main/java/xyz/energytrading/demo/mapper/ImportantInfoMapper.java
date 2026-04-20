package xyz.energytrading.demo.mapper;

import org.mapstruct.Mapper;
import xyz.energytrading.demo.dto.ImportantInfoDTO;
import xyz.energytrading.demo.models.ImportantInfo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImportantInfoMapper {
    ImportantInfoDTO toDto(ImportantInfo entity);
    ImportantInfo toEntity(ImportantInfoDTO dto);
    List<ImportantInfoDTO> toDto(List<ImportantInfo> entity);
    List<ImportantInfo> toEntity(List<ImportantInfoDTO> dto);
}
