package com.application.ev_platform.modules.booking;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    // GET /api/bookings - Get all bookings
    @GetMapping
    public List<BookingResponseDTO> getAllBookings() {
        return service.getAllBookings();
    }

    // GET /api/bookings/{id} - Get booking by ID
    @GetMapping("/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {
        return service.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // GET /api/bookings/user/{userId} - Get bookings by user
    @GetMapping("/user/{userId}")
    public List<BookingResponseDTO> getBookingsByUser(@PathVariable Long userId) {
        return service.getBookingsByUser(userId);
    }

    // GET /api/bookings/station/{stationId} - Get bookings by station
    @GetMapping("/station/{stationId}")
    public List<BookingResponseDTO> getBookingsByStation(@PathVariable Long stationId) {
        return service.getBookingsByStation(stationId);
    }

    // POST /api/bookings - Create new booking
    @PostMapping
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO request) {
        return service.createBooking(request);
    }

    // PUT /api/bookings/{id}/confirm - Confirm booking
    @PutMapping("/{id}/confirm")
    public BookingResponseDTO confirmBooking(@PathVariable Long id) {
        return service.confirmBooking(id);
    }

    // PUT /api/bookings/{id}/start - Start charging
    @PutMapping("/{id}/start")
    public BookingResponseDTO startCharging(@PathVariable Long id) {
        return service.startCharging(id);
    }

    // PUT /api/bookings/{id}/complete - Complete booking
    @PutMapping("/{id}/complete")
    public BookingResponseDTO completeBooking(@PathVariable Long id) {
        return service.completeBooking(id);
    }

    // PUT /api/bookings/{id}/cancel - Cancel booking
    @PutMapping("/{id}/cancel")
    public BookingResponseDTO cancelBooking(@PathVariable Long id) {
        return service.cancelBooking(id);
    }

    // DELETE /api/bookings/{id} - Delete booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
    }
}
