package xyz.energytrading.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.energytrading.demo.dto.ImportantInfoDTO;
import xyz.energytrading.demo.services.ImportantInfoService;

import java.util.List;

@RestController
@RequestMapping("/important")
public class ImportantInfoController {

    @Autowired
    private ImportantInfoService importantInfoService;

    @GetMapping("/{name}")
    public ResponseEntity<List<ImportantInfoDTO>> getImportantInfoByName(@PathVariable String name) {
        return ResponseEntity.ok(importantInfoService.getAllForName(name));
    }
}
