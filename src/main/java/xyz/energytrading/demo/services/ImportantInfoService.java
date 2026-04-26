package xyz.energytrading.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.openapi.example.api.WeatherApiClient;
import xyz.energytrading.demo.dto.ImportantInfoDTO;
import xyz.energytrading.demo.mapper.ImportantInfoMapper;
import xyz.energytrading.demo.models.ImportantInfo;
import xyz.energytrading.demo.repository.ImportantInfoRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ImportantInfoService {
    @Autowired
    ImportantInfoRepository importantInfoRepository;
    @Autowired
    ImportantInfoMapper importantInfoMapper;

    @Autowired
    WeatherApiClient weatherclient;

    public ImportantInfoService() {

    }

    public List<ImportantInfoDTO> getAllForName(String name) {
        List<ImportantInfo> infosByName = importantInfoRepository.findAllByName(name)
                .orElse(Collections.emptyList());
        return importantInfoMapper.toDto(infosByName);
    }
}
