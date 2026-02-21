package com.application.ev_platform.modules.routing;

public class RouteRequestDTO {

    private Long userId;
    private Long startStationId;
    private Long endStationId;
    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;

    // Getters & Setters
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
}
