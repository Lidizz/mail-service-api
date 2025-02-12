package com.lidizz.mailserviceapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "email_records")
public class EmailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject is required")
    @Column(nullable = false)
    private String subject;


    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @NotNull(message = "Sender is required")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    @NotNull(message = "Recipient is required")
    private User recipient;

    @NotBlank(message = "Body is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant sentAt;

//    private LocalDateTime readAt;

    // Default constructor
    public EmailRecord() {

    }

    // Constructor with fields
    public EmailRecord(String subject, String body, User sender, User recipient) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.recipient = recipient;
        this.sentAt = Instant.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


//    public LocalDateTime getReadAt() {
//        return readAt;
//    }
//
//    public void setReadAt(LocalDateTime readAt) {
//        this.readAt = readAt;
//    }

    @Override
    public String toString() {
        return "EmailRecord{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", sender=" + sender.getId() +
                ", recipient=" + recipient.getId() +
                ", body='" + body + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}