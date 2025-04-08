package com.lidizz.mailserviceapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmailRecordDTO {
    @NotBlank(message = "Subject is required")
    private String subject;

    @NotNull(message = "Sender ID is required")
    private Long senderId;

    @NotNull(message = "Recipient ID is required")
    private Long recipientId;

    @NotBlank(message = "Body is required")
    private String body;

    // No-args constructor
    public EmailRecordDTO() {}

    // Args constructor
    public EmailRecordDTO(String subject, Long senderId, Long recipientId, String body) {
        this.subject = subject;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.body = body;
    }

    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}