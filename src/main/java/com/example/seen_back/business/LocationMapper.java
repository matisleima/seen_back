package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonDto;
import com.example.seen_back.domain.Location;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    @Mapping(expression = "java(mapDescriptionFromFeatures(geoJsonDto))", target = "description")
    @Mapping(expression = "java(mapLatitudeFromFeatures(geoJsonDto))", target = "latitude")
    @Mapping(expression = "java(mapLongitudeFromFeatures(geoJsonDto))", target = "longitude")
    Location toEntity(GeoJsonDto geoJsonDto);

    @Mapping(source = "description", target = "properties.description")
    @Mapping(expression = "java(java.util.List.of(location.getLongitude(), location.getLatitude()))", target = "geometry.coordinates")
    @Mapping(source = "id", target = "properties.id")
    GeoJsonDto.Feature toFeature(Location location);

    default GeoJsonDto toDto(List<Location> locations) {
        GeoJsonDto dto = new GeoJsonDto();
        List<GeoJsonDto.Feature> features = new ArrayList<>();

        for (Location location : locations) {
            features.add(toFeature(location));
        }

        dto.setFeatures(features);
        return dto;
    }

    default String mapDescriptionFromFeatures(GeoJsonDto request) {
        if (isValidFeature(request)) {
            return request.getFeatures().get(0).getProperties().getDescription();
        }
        return null;
    }

    default Double mapLatitudeFromFeatures(GeoJsonDto request) {
        if (isValidFeature(request) &&
                request.getFeatures().get(0).getGeometry().getCoordinates() != null &&
                request.getFeatures().get(0).getGeometry().getCoordinates().size() > 0) {
            return request.getFeatures().get(0).getGeometry().getCoordinates().get(1);
        }
        return null;
    }

    default Double mapLongitudeFromFeatures(GeoJsonDto request) {
        if (isValidFeature(request) &&
                request.getFeatures().get(0).getGeometry().getCoordinates() != null &&
                request.getFeatures().get(0).getGeometry().getCoordinates().size() > 1) {
            return request.getFeatures().get(0).getGeometry().getCoordinates().get(0);
        }
        return null;
    }

    default boolean isValidFeature(GeoJsonDto request) {
        return request != null &&
                request.getFeatures() != null &&
                !request.getFeatures().isEmpty() &&
                request.getFeatures().get(0).getProperties() != null &&
                request.getFeatures().get(0).getGeometry() != null;
    }

}