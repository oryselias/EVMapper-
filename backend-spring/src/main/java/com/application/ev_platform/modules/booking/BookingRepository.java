package com.application.ev_platform.modules.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Get all bookings for a user
    List<Booking> findByUserId(Long userId);
    
    // Get all bookings for a station
    List<Booking> findByStationId(Long stationId);
    
    // Get bookings by status
    List<Booking> findByStatus(Booking.BookingStatus status);
    
    // Get user's bookings by status
    List<Booking> findByUserIdAndStatus(Long userId, Booking.BookingStatus status);
}
