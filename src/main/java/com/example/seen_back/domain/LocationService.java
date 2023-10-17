package com.example.seen_back.domain;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Resource
    private LocationRepository locationRepository;

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }
}
