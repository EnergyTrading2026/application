package xyz.energytrading.monitoring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.energytrading.monitoring.dto.AssetDTO;
import xyz.energytrading.monitoring.dto.MetricDTO;
import xyz.energytrading.monitoring.dto.MonitoringDataDTO;
import xyz.energytrading.monitoring.dto.StorageDTO;
import xyz.energytrading.monitoring.dto.SystemDTO;
import xyz.energytrading.monitoring.services.MonitoringService;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * REST Controller for monitoring data.
 * Provides endpoints to retrieve and manage system, asset, storage, and complete monitoring data.
 */
@RestController
@RequestMapping("/api/monitoring")
@Tag(name = "Monitoring", description = "Endpoints for monitoring energy trading systems")
public class MonitoringController {

    private final MonitoringService monitoringService;

    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    // ==================== Complete Monitoring Data Endpoints ====================

    @Operation(summary = "Get the latest complete monitoring data snapshot")
    @GetMapping("/latest")
    public ResponseEntity<MonitoringDataDTO> getLatestMonitoringData() {
        MonitoringDataDTO data = monitoringService.getLatestMonitoringData();
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Get monitoring data at a specific timestamp")
    @GetMapping("/{timestamp}")
    public ResponseEntity<MonitoringDataDTO> getMonitoringDataAtTimestamp(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime timestamp) {
        MonitoringDataDTO data = monitoringService.getMonitoringDataAtTimestamp(timestamp);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Create a complete monitoring data snapshot")
    @PostMapping
    public ResponseEntity<MonitoringDataDTO> createMonitoringData(
            @RequestBody MonitoringDataDTO monitoringDataDTO) {
        MonitoringDataDTO created = monitoringService.createMonitoringData(monitoringDataDTO);
        return ResponseEntity.ok(created);
    }

    // ==================== System Endpoints ====================

    @Operation(summary = "Get all system data")
    @GetMapping("/system")
    public ResponseEntity<List<SystemDTO>> getAllSystems() {
        return ResponseEntity.ok(monitoringService.getAllSystems());
    }

    @Operation(summary = "Get the latest system data")
    @GetMapping("/system/latest")
    public ResponseEntity<SystemDTO> getLatestSystem() {
        return monitoringService.getLatestSystem()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get system data by date range")
    @GetMapping("/system/range")
    public ResponseEntity<List<SystemDTO>> getSystemsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return ResponseEntity.ok(monitoringService.getSystemsByDateRange(start, end));
    }

    @Operation(summary = "Create system data")
    @PostMapping("/system")
    public ResponseEntity<SystemDTO> createSystem(@RequestBody SystemDTO systemDTO) {
        return ResponseEntity.ok(monitoringService.createSystem(systemDTO));
    }

    // ==================== Asset Endpoints ====================

    @Operation(summary = "Get all asset data")
    @GetMapping("/assets")
    public ResponseEntity<List<AssetDTO>> getAllAssets() {
        return ResponseEntity.ok(monitoringService.getAllAssets());
    }

    @Operation(summary = "Get assets by timestamp")
    @GetMapping("/assets/{timestamp}")
    public ResponseEntity<List<AssetDTO>> getAssetsByTimestamp(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime timestamp) {
        return ResponseEntity.ok(monitoringService.getAssetsByTimestamp(timestamp));
    }

    @Operation(summary = "Get assets by date range")
    @GetMapping("/assets/range")
    public ResponseEntity<List<AssetDTO>> getAssetsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return ResponseEntity.ok(monitoringService.getAssetsByDateRange(start, end));
    }

    @Operation(summary = "Create asset data")
    @PostMapping("/assets")
    public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO assetDTO) {
        return ResponseEntity.ok(monitoringService.createAsset(assetDTO));
    }

    // ==================== Storage Endpoints ====================

    @Operation(summary = "Get all storage data")
    @GetMapping("/storage")
    public ResponseEntity<List<StorageDTO>> getAllStorages() {
        return ResponseEntity.ok(monitoringService.getAllStorages());
    }

    @Operation(summary = "Get the latest storage data")
    @GetMapping("/storage/latest")
    public ResponseEntity<StorageDTO> getLatestStorage() {
        return monitoringService.getLatestStorage()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get storage data by date range")
    @GetMapping("/storage/range")
    public ResponseEntity<List<StorageDTO>> getStoragesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return ResponseEntity.ok(monitoringService.getStoragesByDateRange(start, end));
    }

    @Operation(summary = "Create storage data")
    @PostMapping("/storage")
    public ResponseEntity<StorageDTO> createStorage(@RequestBody StorageDTO storageDTO) {
        return ResponseEntity.ok(monitoringService.createStorage(storageDTO));
    }

    // ==================== Metric Endpoints ====================

    @Operation(summary = "Get metrics by storage ID")
    @GetMapping("/storage/{storageId}/metrics")
    public ResponseEntity<List<MetricDTO>> getMetricsByStorage(@PathVariable Long storageId) {
        return ResponseEntity.ok(monitoringService.getMetricsByStorage(storageId));
    }

    @Operation(summary = "Create metric data")
    @PostMapping("/metrics")
    public ResponseEntity<MetricDTO> createMetric(@RequestBody MetricDTO metricDTO) {
        return ResponseEntity.ok(monitoringService.createMetric(metricDTO));
    }
}
