package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonDto;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LocationController {

    @Resource
    private LocationsService locationsService;

    @PostMapping("/add")
    public void addLocation(@RequestBody GeoJsonDto request) {
        locationsService.addLocation(request);
    }

    @GetMapping("/get-all")
    public GeoJsonDto getLocations() {
        return locationsService.getLocations();
    }
}
