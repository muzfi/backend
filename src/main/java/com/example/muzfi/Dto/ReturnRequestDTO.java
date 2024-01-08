package com.example.muzfi.Dto;


public class ReturnRequestDTO {
    private Long productId;
    private String reason;
    private String message;
    

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // other fields, getters, setters
}
