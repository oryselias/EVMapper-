package com.application.ev_platform.modules.station;

public class StationResponseDTO {

    private Long id;
    private String name;
    private String address;
    private double lat;
    private double lng;
    private Boolean active;

    public StationResponseDTO(Long id, String name, String address, double lat, double lng, Boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.active = active;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public Boolean getActive() { return active; }
}
