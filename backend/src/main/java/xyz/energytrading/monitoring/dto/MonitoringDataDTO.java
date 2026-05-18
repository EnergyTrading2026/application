package xyz.energytrading.monitoring.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO that matches the frontend MonitoringData interface.
 * This is the complete monitoring data structure expected by the Angular frontend.
 */
public class MonitoringDataDTO {

    private SystemDTO system;
    private List<AssetDTO> assets = new ArrayList<>();
    private StorageDTO storage;

    public MonitoringDataDTO() {
    }

    public MonitoringDataDTO(SystemDTO system, List<AssetDTO> assets, StorageDTO storage) {
        this.system = system;
        this.assets = assets;
        this.storage = storage;
    }

    public SystemDTO getSystem() {
        return system;
    }

    public void setSystem(SystemDTO system) {
        this.system = system;
    }

    public List<AssetDTO> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetDTO> assets) {
        this.assets = assets;
    }

    public StorageDTO getStorage() {
        return storage;
    }

    public void setStorage(StorageDTO storage) {
        this.storage = storage;
    }
}
