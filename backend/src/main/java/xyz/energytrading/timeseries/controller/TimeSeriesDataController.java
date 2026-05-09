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

    @GetMapping("/{id}")
    public ResponseEntity<TimeSeriesDataDTO> getTimeSeriesDataById(@PathVariable Long id) {
        TimeSeriesDataDTO result = timeSeriesDataService.getById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<TimeSeriesDataDTO>> getTimeSeriesDataByTag(@PathVariable String tag) {
        return ResponseEntity.ok(timeSeriesDataService.getAllByTag(tag));
    }

    @GetMapping("/range")
    public ResponseEntity<List<TimeSeriesDataDTO>> getTimeSeriesDataByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return ResponseEntity.ok(timeSeriesDataService.getAllByDateRange(start, end));
    }

    @GetMapping("/tag/{tag}/range")
    public ResponseEntity<List<TimeSeriesDataDTO>> getTimeSeriesDataByTagAndDateRange(
            @PathVariable String tag,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end) {
        return ResponseEntity.ok(timeSeriesDataService.getAllByTagAndDateRange(tag, start, end));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSeriesData(@PathVariable Long id) {
        timeSeriesDataService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
