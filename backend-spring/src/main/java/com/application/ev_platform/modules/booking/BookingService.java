package com.application.ev_platform.modules.booking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository repo;

    public BookingService(BookingRepository repo) {
        this.repo = repo;
    }

    // GET all bookings
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAllBookings() {
        return repo.findAll().stream()
                .map(BookingResponseDTO::new)
                .toList();
    }

    // GET booking by ID
    @Transactional(readOnly = true)
    public Optional<BookingResponseDTO> getBookingById(Long id) {
        return repo.findById(id).map(BookingResponseDTO::new);
    }

    // GET bookings by user
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getBookingsByUser(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(BookingResponseDTO::new)
                .toList();
    }

    // GET bookings by station
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getBookingsByStation(Long stationId) {
        return repo.findByStationId(stationId).stream()
                .map(BookingResponseDTO::new)
                .toList();
    }

    // POST - Create new booking
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setStationId(request.getStationId());
        booking.setStationName(request.getStationName());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus(Booking.BookingStatus.PENDING);
        
        // Calculate total price
        if (request.getPricePerHour() != null) {
            long hours = Duration.between(request.getStartTime(), request.getEndTime()).toHours();
            booking.setTotalPrice(hours * request.getPricePerHour());
        }
        
        return new BookingResponseDTO(repo.save(booking));
    }

    // PUT - Confirm booking
    @Transactional
    public BookingResponseDTO confirmBooking(Long id) {
        Booking booking = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        return new BookingResponseDTO(repo.save(booking));
    }

    // PUT - Start charging (check-in)
    @Transactional
    public BookingResponseDTO startCharging(Long id) {
        Booking booking = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.IN_PROGRESS);
        return new BookingResponseDTO(repo.save(booking));
    }

    // PUT - Complete booking
    @Transactional
    public BookingResponseDTO completeBooking(Long id) {
        Booking booking = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.COMPLETED);
        return new BookingResponseDTO(repo.save(booking));
    }

    // PUT - Cancel booking
    @Transactional
    public BookingResponseDTO cancelBooking(Long id) {
        Booking booking = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        return new BookingResponseDTO(repo.save(booking));
    }

    // DELETE - Remove booking
    @Transactional
    public void deleteBooking(Long id) {
        repo.deleteById(id);
    }
}
