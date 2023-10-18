package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonCollectionDto;
import com.example.seen_back.business.dto.GeoJsonPointDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    @Resource
    private LocationsService locationsService;

    @GetMapping("/get-all")
    @Operation(summary = "Tagastab kõik aktiivsed andmebaasi salvestatud asukohad.")
    public GeoJsonCollectionDto getLocations() {
        return locationsService.getLocations();
    }

    @PostMapping("/add")
    @Operation(summary = "Lisab andmebaasi ühe asukoha andmed.")
    public void addLocation(@RequestBody GeoJsonPointDto request) {
        locationsService.addLocation(request);
    }

    @PutMapping("/edit")
    @Operation(summary = "Muudab ühe asukoha andmed.")
    public void editLocation(@RequestBody GeoJsonPointDto request) {
        locationsService.editLocation(request);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Muudab ühe asukoha statuse mitteaktiivseks.")
    public void deleteLocation(@RequestParam Integer id) {
        locationsService.deleteLocation(id);
    }
}
