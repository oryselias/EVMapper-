package com.application.ev_platform.modules.payment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // GET /api/payments - Get all payments
    @GetMapping
    public List<PaymentResponseDTO> getAllPayments() {
        return service.getAllPayments();
    }

    // GET /api/payments/{id} - Get payment by ID
    @GetMapping("/{id}")
    public PaymentResponseDTO getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // GET /api/payments/user/{userId} - Get payments by user
    @GetMapping("/user/{userId}")
    public List<PaymentResponseDTO> getPaymentsByUser(@PathVariable Long userId) {
        return service.getPaymentsByUser(userId);
    }

    // GET /api/payments/booking/{bookingId} - Get payments by booking
    @GetMapping("/booking/{bookingId}")
    public List<PaymentResponseDTO> getPaymentsByBooking(@PathVariable Long bookingId) {
        return service.getPaymentsByBooking(bookingId);
    }

    // POST /api/payments - Create new payment
    @PostMapping
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO request) {
        return service.createPayment(request);
    }

    // PUT /api/payments/{id}/process - Process payment
    @PutMapping("/{id}/process")
    public PaymentResponseDTO processPayment(@PathVariable Long id) {
        return service.processPayment(id);
    }

    // PUT /api/payments/{id}/fail - Mark payment as failed
    @PutMapping("/{id}/fail")
    public PaymentResponseDTO failPayment(@PathVariable Long id) {
        return service.failPayment(id);
    }

    // PUT /api/payments/{id}/refund - Refund payment
    @PutMapping("/{id}/refund")
    public PaymentResponseDTO refundPayment(@PathVariable Long id) {
        return service.refundPayment(id);
    }

    // DELETE /api/payments/{id} - Delete payment
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
    }
}
