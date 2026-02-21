package com.application.ev_platform.modules.vehicle;

import java.time.Instant;

public class VehicleResponseDTO {

    private Long id;
    private Long userId;
    private String make;
    private String model;
    private String registrationNumber;
    private String color;
    private Double batteryCapacityKwh;
    private String connectorType;
    private Boolean active;
    private Instant createdAt;

    public VehicleResponseDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.userId = vehicle.getUserId();
        this.make = vehicle.getMake();
        this.model = vehicle.getModel();
        this.registrationNumber = vehicle.getRegistrationNumber();
        this.color = vehicle.getColor();
        this.batteryCapacityKwh = vehicle.getBatteryCapacityKwh();
        this.connectorType = vehicle.getConnectorType();
        this.active = vehicle.getActive();
        this.createdAt = vehicle.getCreatedAt();
    }

    // Getters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getRegistrationNumber() { return registrationNumber; }
    public String getColor() { return color; }
    public Double getBatteryCapacityKwh() { return batteryCapacityKwh; }
    public String getConnectorType() { return connectorType; }
    public Boolean getActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
}
