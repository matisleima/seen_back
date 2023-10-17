package com.example.seen_back.business.dto;


import lombok.Data;
import java.util.List;

@Data
public class GeoJsonPointDto {
    private String type = "Feature";
    private Geometry geometry;
    private Properties properties;

    @Data
    public static class Geometry {
        private String type = "Point";
        private List<Double> coordinates;
    }

    @Data
    public static class Properties {
        private Long id;
        private String description;
    }

}

