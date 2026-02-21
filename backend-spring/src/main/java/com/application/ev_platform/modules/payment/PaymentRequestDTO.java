package com.application.ev_platform.modules.payment;

public class PaymentRequestDTO {

    private Long bookingId;
    private Long userId;
    private Double amount;
    private String method; // CARD, UPI, WALLET, BANK_TRANSFER

    // Getters & Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
