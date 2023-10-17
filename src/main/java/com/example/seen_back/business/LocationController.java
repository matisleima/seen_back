package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonCollectionDto;
import com.example.seen_back.business.dto.GeoJsonPointDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LocationController {

    @Resource
    private LocationsService locationsService;

    @PostMapping("/add")
    @Operation(summary = "Lisab andmebaasi ühe asukoha andmed.")
    public void addLocation(@RequestBody GeoJsonPointDto request) {
        locationsService.addLocation(request);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Tagastab kõik andmebaasi salvestatud asukohad.")
    public GeoJsonCollectionDto getLocations() {
        return locationsService.getLocations();
    }
}
