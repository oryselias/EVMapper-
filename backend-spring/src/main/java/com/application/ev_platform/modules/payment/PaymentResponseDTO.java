package com.application.ev_platform.modules.payment;

import java.time.Instant;

public class PaymentResponseDTO {

    private Long id;
    private Long bookingId;
    private Long userId;
    private Double amount;
    private String status;
    private String method;
    private String transactionId;
    private Instant createdAt;
    private Instant paidAt;

    public PaymentResponseDTO(Payment payment) {
        this.id = payment.getId();
        this.bookingId = payment.getBookingId();
        this.userId = payment.getUserId();
        this.amount = payment.getAmount();
        this.status = payment.getStatus().name();
        this.method = payment.getMethod().name();
        this.transactionId = payment.getTransactionId();
        this.createdAt = payment.getCreatedAt();
        this.paidAt = payment.getPaidAt();
    }

    // Getters
    public Long getId() { return id; }
    public Long getBookingId() { return bookingId; }
    public Long getUserId() { return userId; }
    public Double getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getMethod() { return method; }
    public String getTransactionId() { return transactionId; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getPaidAt() { return paidAt; }
}
