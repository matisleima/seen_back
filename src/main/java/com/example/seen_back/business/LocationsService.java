package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonCollectionDto;
import com.example.seen_back.business.dto.GeoJsonPointDto;
import com.example.seen_back.domain.Location;
import com.example.seen_back.domain.LocationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {

    @Resource
    private LocationService locationService;
    @Resource
    private LocationMapper locationMapper;

    public void addLocation(GeoJsonPointDto request) {
        Location location = locationMapper.toEntity(request);
        locationService.saveLocation(location);
    }

    public GeoJsonCollectionDto getLocations() {
        List<Location> locations = locationService.getLocations();
        return locationMapper.toDto(locations);
    }
}
