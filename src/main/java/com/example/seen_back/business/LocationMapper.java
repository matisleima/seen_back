package com.example.seen_back.business;

import com.example.seen_back.business.dto.GeoJsonCollectionDto;
import com.example.seen_back.business.dto.GeoJsonPointDto;
import com.example.seen_back.domain.Location;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    @Mapping(source = "description", target = "properties.description")
    @Mapping(expression = "java(java.util.List.of(location.getLongitude(), location.getLatitude()))", target = "geometry.coordinates")
    @Mapping(source = "id", target = "properties.id")
    @Mapping(source = "status", target = "properties.status")
    GeoJsonCollectionDto.Feature toFeature(Location location);

    default GeoJsonCollectionDto toDto(List<Location> locations) {
        GeoJsonCollectionDto dto = new GeoJsonCollectionDto();
        List<GeoJsonCollectionDto.Feature> features = new ArrayList<>();

        for (Location location : locations) {
            features.add(toFeature(location));
        }

        dto.setFeatures(features);
        return dto;
    }

    @Mapping(source = "properties.description", target = "description")
    @Mapping(expression = "java(mapLongitudeFromFeatures(request))", target = "longitude")
    @Mapping(expression = "java(mapLatitudeFromFeatures(request))", target = "latitude")
    Location toEntity(GeoJsonPointDto request);

    default Double mapLongitudeFromFeatures(GeoJsonPointDto request) {
    if (isValidFeature(request) &&
            request.getGeometry().getCoordinates() != null &&
            request.getGeometry().getCoordinates().size() > 1) {
        return request.getGeometry().getCoordinates().get(0);
    }
    return null;
}

    default Double mapLatitudeFromFeatures(GeoJsonPointDto request) {
        if (isValidFeature(request) &&
                request.getGeometry().getCoordinates() != null &&
                request.getGeometry().getCoordinates().size() > 0) {
            return request.getGeometry().getCoordinates().get(1);
        }
        return null;
    }

    default boolean isValidFeature(GeoJsonPointDto request) {
        return request != null &&
                request.getProperties() != null &&
                request.getGeometry() != null;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "properties.description", target = "description")
    @Mapping(expression = "java(mapLongitudeFromFeatures(request))", target = "longitude")
    @Mapping(expression = "java(mapLatitudeFromFeatures(request))", target = "latitude")
    Location partialUpdate(GeoJsonPointDto request, @MappingTarget Location location);

}