package xyz.energytrading.monitoring.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.energytrading.monitoring.dto.AssetDTO;
import xyz.energytrading.monitoring.dto.MetricDTO;
import xyz.energytrading.monitoring.dto.MonitoringDataDTO;
import xyz.energytrading.monitoring.dto.StorageDTO;
import xyz.energytrading.monitoring.dto.SystemDTO;
import xyz.energytrading.monitoring.mapper.AssetMapper;
import xyz.energytrading.monitoring.mapper.MetricMapper;
import xyz.energytrading.monitoring.mapper.StorageMapper;
import xyz.energytrading.monitoring.mapper.SystemMapper;
import xyz.energytrading.monitoring.models.AssetEntity;
import xyz.energytrading.monitoring.models.MetricEntity;
import xyz.energytrading.monitoring.models.StorageEntity;
import xyz.energytrading.monitoring.models.SystemEntity;
import xyz.energytrading.monitoring.repository.AssetRepository;
import xyz.energytrading.monitoring.repository.MetricRepository;
import xyz.energytrading.monitoring.repository.StorageRepository;
import xyz.energytrading.monitoring.repository.SystemRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing monitoring data.
 * Provides methods to create, retrieve, and manage system, asset, and storage monitoring data.
 */
@Service
@Transactional
public class MonitoringService {

    private final SystemRepository systemRepository;
    private final AssetRepository assetRepository;
    private final StorageRepository storageRepository;
    private final MetricRepository metricRepository;
    private final SystemMapper systemMapper;
    private final AssetMapper assetMapper;
    private final StorageMapper storageMapper;
    private final MetricMapper metricMapper;

    public MonitoringService(
            SystemRepository systemRepository,
            AssetRepository assetRepository,
            StorageRepository storageRepository,
            MetricRepository metricRepository,
            SystemMapper systemMapper,
            AssetMapper assetMapper,
            StorageMapper storageMapper,
            MetricMapper metricMapper) {
        this.systemRepository = systemRepository;
        this.assetRepository = assetRepository;
        this.storageRepository = storageRepository;
        this.metricRepository = metricRepository;
        this.systemMapper = systemMapper;
        this.assetMapper = assetMapper;
        this.storageMapper = storageMapper;
        this.metricMapper = metricMapper;
    }

    // ==================== System Operations ====================

    public SystemDTO createSystem(SystemDTO systemDTO) {
        SystemEntity entity = systemMapper.toEntity(systemDTO);
        SystemEntity saved = systemRepository.save(entity);
        return systemMapper.toDto(saved);
    }

    public Optional<SystemDTO> getLatestSystem() {
        return systemRepository.findTopByOrderByTimestampDesc()
                .map(systemMapper::toDto);
    }

    public List<SystemDTO> getAllSystems() {
        return systemMapper.toDto(systemRepository.findAllByOrderByTimestampDesc());
    }

    public List<SystemDTO> getSystemsByDateRange(OffsetDateTime start, OffsetDateTime end) {
        return systemMapper.toDto(
                systemRepository.findAllByTimestampBetweenOrderByTimestampDesc(start, end)
        );
    }

    // ==================== Asset Operations ====================

    public AssetDTO createAsset(AssetDTO assetDTO) {
        AssetEntity entity = assetMapper.toEntity(assetDTO);
        AssetEntity saved = assetRepository.save(entity);
        return assetMapper.toDto(saved);
    }

    public List<AssetDTO> getAllAssets() {
        return assetMapper.toDto(assetRepository.findAllByOrderByTimestampDesc());
    }

    public List<AssetDTO> getAssetsByTimestamp(OffsetDateTime timestamp) {
        return assetMapper.toDto(assetRepository.findAllByTimestamp(timestamp));
    }

    public List<AssetDTO> getAssetsByDateRange(OffsetDateTime start, OffsetDateTime end) {
        return assetMapper.toDto(
                assetRepository.findAllByTimestampBetweenOrderByTimestampDesc(start, end)
        );
    }

    // ==================== Storage Operations ====================

    public StorageDTO createStorage(StorageDTO storageDTO) {
        StorageEntity entity = storageMapper.toEntity(storageDTO);
        StorageEntity saved = storageRepository.save(entity);
        return storageMapper.toDto(saved);
    }

    public Optional<StorageDTO> getLatestStorage() {
        return storageRepository.findTopByOrderByTimestampDesc()
                .map(storageMapper::toDto);
    }

    public List<StorageDTO> getAllStorages() {
        return storageMapper.toDto(storageRepository.findAllByOrderByTimestampDesc());
    }

    public List<StorageDTO> getStoragesByDateRange(OffsetDateTime start, OffsetDateTime end) {
        return storageMapper.toDto(
                storageRepository.findAllByTimestampBetweenOrderByTimestampDesc(start, end)
        );
    }

    // ==================== Metric Operations ====================

    public MetricDTO createMetric(MetricDTO metricDTO) {
        MetricEntity entity = metricMapper.toEntity(metricDTO);
        // Need to set the storage reference manually
        if (metricDTO.getStorageId() != null) {
            StorageEntity storage = storageRepository.findById(metricDTO.getStorageId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Storage with id " + metricDTO.getStorageId() + " not found"));
            entity.setStorage(storage);
        }
        MetricEntity saved = metricRepository.save(entity);
        return metricMapper.toDto(saved);
    }

    public List<MetricDTO> getMetricsByStorage(Long storageId) {
        return metricMapper.toDto(metricRepository.findAllByStorageId(storageId));
    }

    // ==================== Composite Monitoring Data Operations ====================

    /**
     * Gets the latest complete monitoring data snapshot.
     * Returns system, all assets, and storage with their metrics for the latest timestamp.
     */
    @Transactional(readOnly = true)
    public MonitoringDataDTO getLatestMonitoringData() {
        MonitoringDataDTO result = new MonitoringDataDTO();

        // Get latest system
        getLatestSystem().ifPresent(result::setSystem);

        // Get all latest assets
        result.setAssets(new ArrayList<>(getAllAssets()));

        // Get latest storage with metrics
        getLatestStorage().ifPresent(storageDTO -> {
            // Enhance storage with its metrics
            if (storageDTO.getId() != null) {
                List<MetricDTO> metrics = getMetricsByStorage(storageDTO.getId());
                storageDTO.setMetrics(metrics);
            }
            result.setStorage(storageDTO);
        });

        return result;
    }

    /**
     * Gets monitoring data for a specific timestamp.
     */
    @Transactional(readOnly = true)
    public MonitoringDataDTO getMonitoringDataAtTimestamp(OffsetDateTime timestamp) {
        MonitoringDataDTO result = new MonitoringDataDTO();

        // Get system at timestamp (or nearest before)
        systemRepository.findAllByTimestampBetweenOrderByTimestampDesc(
                        timestamp.minusHours(1), timestamp.plusHours(1))
                .stream().findFirst()
                .map(systemMapper::toDto)
                .ifPresent(result::setSystem);

        // Get assets at timestamp
        result.setAssets(new ArrayList<>(getAssetsByTimestamp(timestamp)));

        // Get storage at timestamp with metrics
        storageRepository.findAllByTimestampBetweenOrderByTimestampDesc(
                        timestamp.minusHours(1), timestamp.plusHours(1))
                .stream().findFirst()
                .map(storageMapper::toDto)
                .ifPresent(storageDTO -> {
                    if (storageDTO.getId() != null) {
                        List<MetricDTO> metrics = getMetricsByStorage(storageDTO.getId());
                        storageDTO.setMetrics(metrics);
                    }
                    result.setStorage(storageDTO);
                });

        return result;
    }

    /**
     * Creates a complete monitoring data snapshot.
     */
    @Transactional
    public MonitoringDataDTO createMonitoringData(MonitoringDataDTO monitoringDataDTO) {
        OffsetDateTime timestamp = OffsetDateTime.now();

        // Set timestamp for all components
        if (monitoringDataDTO.getSystem() != null) {
            monitoringDataDTO.getSystem().setTimestamp(timestamp);
            createSystem(monitoringDataDTO.getSystem());
        }

        if (monitoringDataDTO.getAssets() != null) {
            for (AssetDTO asset : monitoringDataDTO.getAssets()) {
                asset.setTimestamp(timestamp);
                createAsset(asset);
            }
        }

        if (monitoringDataDTO.getStorage() != null) {
            monitoringDataDTO.getStorage().setTimestamp(timestamp);
            StorageDTO savedStorage = createStorage(monitoringDataDTO.getStorage());
            
            // Create metrics for the storage
            if (monitoringDataDTO.getStorage().getMetrics() != null) {
                for (MetricDTO metric : monitoringDataDTO.getStorage().getMetrics()) {
                    metric.setTimestamp(timestamp);
                    metric.setStorageId(savedStorage.getId());
                    createMetric(metric);
                }
            }
        }

        return monitoringDataDTO;
    }
}
