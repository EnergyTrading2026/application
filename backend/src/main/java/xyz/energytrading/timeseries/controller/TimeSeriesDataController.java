package xyz.energytrading.timeseries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.energytrading.timeseries.dto.TimeSeriesDataDTO;
import xyz.energytrading.timeseries.services.TimeSeriesDataService;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/time-series")
public class TimeSeriesDataController {

    @Autowired
    private TimeSeriesDataService timeSeriesDataService;

    @PostMapping
    public ResponseEntity<TimeSeriesDataDTO> createTimeSeriesData(
            @RequestBody TimeSeriesDataDTO timeSeriesDataDTO) {
        TimeSeriesDataDTO created = timeSeriesDataService.create(timeSeriesDataDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<TimeSeriesDataDTO>> getAllTimeSeriesData() {
        return ResponseEntity.ok(timeSeriesDataService.getAll());
    }

    @GetMapping("/{timestamp}/{modelIdentifier}")
    public ResponseEntity<TimeSeriesDataDTO> getTimeSeriesDataById(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime timestamp,
            @PathVariable String modelIdentifier) {
        TimeSeriesDataDTO result = timeSeriesDataService.getById(timestamp, modelIdentifier);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/range")
    public ResponseEntity<List<TimeSeriesDataDTO>> getTimeSeriesDataByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return ResponseEntity.ok(timeSeriesDataService.getAllByDateRange(start, end));
    }

    @DeleteMapping("/{timestamp}/{modelIdentifier}")
    public ResponseEntity<Void> deleteTimeSeriesData(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime timestamp,
            @PathVariable String modelIdentifier) {
        timeSeriesDataService.deleteById(timestamp, modelIdentifier);
        return ResponseEntity.noContent().build();
    }
}
