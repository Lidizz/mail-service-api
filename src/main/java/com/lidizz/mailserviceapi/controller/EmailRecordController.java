package com.lidizz.mailserviceapi.controller;

import com.lidizz.mailserviceapi.model.EmailRecord;
import com.lidizz.mailserviceapi.dto.EmailRecordDTO;
import com.lidizz.mailserviceapi.model.User;
import com.lidizz.mailserviceapi.service.EmailRecordService;
import com.lidizz.mailserviceapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailRecordController {

    private final EmailRecordService emailRecordService;
    private final UserService userService;

    @Autowired
    public EmailRecordController(EmailRecordService emailRecordService, UserService userService) {
        this.emailRecordService = emailRecordService;
        this.userService = userService;
    }

    @GetMapping
    public List<EmailRecord> getAllEmails() {
        return emailRecordService.getAllEmails();
    }

    @GetMapping("/{id}")
    public EmailRecord getEmailById(@PathVariable Long id) {
        return emailRecordService.getEmailById(id);
    }

    @GetMapping("/sender/{senderId}")
    public List<EmailRecord> getAllBySender(@PathVariable Long senderId) {
        User sender = userService.getUserById(senderId);
        return emailRecordService.getAllBySender(sender);
    }

    @GetMapping("/recipient/{recipientId}")
    public List<EmailRecord> getAllByRecipient(@PathVariable Long recipientId) {
        User recipient = userService.getUserById(recipientId);
        return emailRecordService.getAllByRecipient(recipient);
    }

    @PostMapping
    public ResponseEntity<EmailRecord> createEmail(@Valid @RequestBody EmailRecordDTO emailDTO) {
        // Fetch sender and recipient by ID
        User sender = userService.getUserById(emailDTO.getSenderId());
        User recipient = userService.getUserById(emailDTO.getRecipientId());

        // Create EmailRecord entity
        EmailRecord emailRecord = new EmailRecord(
                emailDTO.getSubject(),
                emailDTO.getBody(),
                sender,
                recipient
        );

        EmailRecord createdEmailRecord = emailRecordService.createEmail(emailRecord);
        return new ResponseEntity<>(createdEmailRecord, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailById(@PathVariable Long id) {
        emailRecordService.deleteEmailById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}