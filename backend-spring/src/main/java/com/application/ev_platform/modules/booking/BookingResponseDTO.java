package com.application.ev_platform.modules.booking;

import java.time.Instant;

public class BookingResponseDTO {

    private Long id;
    private Long userId;
    private Long stationId;
    private String stationName;
    private Instant startTime;
    private Instant endTime;
    private String status;
    private Instant createdAt;
    private Double totalPrice;

    public BookingResponseDTO(Booking booking) {
        this.id = booking.getId();
        this.userId = booking.getUserId();
        this.stationId = booking.getStationId();
        this.stationName = booking.getStationName();
        this.startTime = booking.getStartTime();
        this.endTime = booking.getEndTime();
        this.status = booking.getStatus().name();
        this.createdAt = booking.getCreatedAt();
        this.totalPrice = booking.getTotalPrice();
    }

    // Getters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getStationId() { return stationId; }
    public String getStationName() { return stationName; }
    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Double getTotalPrice() { return totalPrice; }
}
