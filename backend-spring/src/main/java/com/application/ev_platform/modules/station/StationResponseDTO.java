package com.application.ev_platform.modules.station;

public class StationResponseDTO {

    private Long id;
    private String name;
    private String address;
    private double lat;
    private double lng;

    public StationResponseDTO(Long id, String name, String address, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
}
