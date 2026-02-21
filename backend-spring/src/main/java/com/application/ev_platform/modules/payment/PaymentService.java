package com.application.ev_platform.modules.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    // GET all payments
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getAllPayments() {
        return repo.findAll().stream()
                .map(PaymentResponseDTO::new)
                .toList();
    }

    // GET payment by ID
    @Transactional(readOnly = true)
    public Optional<PaymentResponseDTO> getPaymentById(Long id) {
        return repo.findById(id).map(PaymentResponseDTO::new);
    }

    // GET payments by user
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsByUser(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(PaymentResponseDTO::new)
                .toList();
    }

    // GET payments by booking
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsByBooking(Long bookingId) {
        return repo.findByBookingId(bookingId).stream()
                .map(PaymentResponseDTO::new)
                .toList();
    }

    // POST - Create payment (simulated - no actual payment gateway)
    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {
        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setStatus(Payment.PaymentStatus.PENDING);
        
        if (request.getMethod() != null) {
            try {
                payment.setMethod(Payment.PaymentMethod.valueOf(request.getMethod()));
            } catch (IllegalArgumentException e) {
                payment.setMethod(Payment.PaymentMethod.CARD);
            }
        }
        
        // Generate a mock transaction ID
        payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        
        return new PaymentResponseDTO(repo.save(payment));
    }

    // PUT - Process payment (simulated)
    @Transactional
    public PaymentResponseDTO processPayment(Long id) {
        Payment payment = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        
        payment.setStatus(Payment.PaymentStatus.PROCESSING);
        
        // Simulate successful payment
        payment.setStatus(Payment.PaymentStatus.COMPLETED);
        payment.setPaidAt(Instant.now());
        
        return new PaymentResponseDTO(repo.save(payment));
    }

    // PUT - Fail payment
    @Transactional
    public PaymentResponseDTO failPayment(Long id) {
        Payment payment = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(Payment.PaymentStatus.FAILED);
        return new PaymentResponseDTO(repo.save(payment));
    }

    // PUT - Refund payment
    @Transactional
    public PaymentResponseDTO refundPayment(Long id) {
        Payment payment = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(Payment.PaymentStatus.REFUNDED);
        return new PaymentResponseDTO(repo.save(payment));
    }

    // DELETE - Remove payment
    @Transactional
    public void deletePayment(Long id) {
        repo.deleteById(id);
    }
}
