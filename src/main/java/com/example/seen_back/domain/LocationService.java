package com.example.seen_back.domain;

import com.example.seen_back.validation.Status;
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

    public List<Location> getActiveLocations() {
        return locationRepository.findLocationsBy(Status.ACTIVE.getLetter());
    }

    public Location getLocationBy(Integer id) {
        return locationRepository.getLocationBy(id);
    }
}
