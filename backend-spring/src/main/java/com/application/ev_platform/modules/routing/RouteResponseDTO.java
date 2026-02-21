package com.application.ev_platform.modules.routing;

import java.time.Instant;

public class RouteResponseDTO {

    private Long id;
    private Long userId;
    private Long startStationId;
    private Long endStationId;
    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;
    private String geometry;
    private Double distanceMeters;
    private Double durationSeconds;
    private Instant createdAt;

    public RouteResponseDTO(Route route) {
        this.id = route.getId();
        this.userId = route.getUserId();
        this.startStationId = route.getStartStationId();
        this.endStationId = route.getEndStationId();
        this.startLat = route.getStartLat();
        this.startLng = route.getStartLng();
        this.endLat = route.getEndLat();
        this.endLng = route.getEndLng();
        this.geometry = route.getGeometry();
        this.distanceMeters = route.getDistanceMeters();
        this.durationSeconds = route.getDurationSeconds();
        this.createdAt = route.getCreatedAt();
    }

    // Getters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getStartStationId() { return startStationId; }
    public Long getEndStationId() { return endStationId; }
    public Double getStartLat() { return startLat; }
    public Double getStartLng() { return startLng; }
    public Double getEndLat() { return endLat; }
    public Double getEndLng() { return endLng; }
    public String getGeometry() { return geometry; }
    public Double getDistanceMeters() { return distanceMeters; }
    public Double getDurationSeconds() { return durationSeconds; }
    public Instant getCreatedAt() { return createdAt; }
}
