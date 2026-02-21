package com.application.ev_platform.modules.routing;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long startStationId;
    private Long endStationId;

    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;

    @Column(columnDefinition = "TEXT")
    private String geometry; // GeoJSON of the route

    private Double distanceMeters;
    private Double durationSeconds;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getStartStationId() { return startStationId; }
    public void setStartStationId(Long startStationId) { this.startStationId = startStationId; }

    public Long getEndStationId() { return endStationId; }
    public void setEndStationId(Long endStationId) { this.endStationId = endStationId; }

    public Double getStartLat() { return startLat; }
    public void setStartLat(Double startLat) { this.startLat = startLat; }

    public Double getStartLng() { return startLng; }
    public void setStartLng(Double startLng) { this.startLng = startLng; }

    public Double getEndLat() { return endLat; }
    public void setEndLat(Double endLat) { this.endLat = endLat; }

    public Double getEndLng() { return endLng; }
    public void setEndLng(Double endLng) { this.endLng = endLng; }

    public String getGeometry() { return geometry; }
    public void setGeometry(String geometry) { this.geometry = geometry; }

    public Double getDistanceMeters() { return distanceMeters; }
    public void setDistanceMeters(Double distanceMeters) { this.distanceMeters = distanceMeters; }

    public Double getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Double durationSeconds) { this.durationSeconds = durationSeconds; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
