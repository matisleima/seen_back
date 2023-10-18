package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonCollectionDto;
import com.example.seen_back.business.dto.GeoJsonPointDto;
import com.example.seen_back.domain.Location;
import com.example.seen_back.domain.LocationService;
import com.example.seen_back.validation.Status;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationsService {

    @Resource
    private LocationService locationService;
    @Resource
    private LocationMapper locationMapper;

    public void addLocation(GeoJsonPointDto request) {
        Location location = locationMapper.toEntity(request);
        location.setStatus(Status.ACTIVE.getLetter());
        locationService.saveLocation(location);
    }

    public GeoJsonCollectionDto getLocations() {
        List<Location> locations = locationService.getActiveLocations();
        return locationMapper.toDto(locations);
    }

    public void deleteLocation(Integer id) {
        Location location = locationService.getLocationBy(id);
        location.setStatus(Status.DEACTIVATED.getLetter());
        locationService.saveLocation(location);
    }

    @Transactional
    public void editLocation(GeoJsonPointDto request) {
        Location location = locationService.getLocationBy(request.getProperties().getId());
        locationMapper.partialUpdate(request, location);
        locationService.saveLocation(location);
    }
}
