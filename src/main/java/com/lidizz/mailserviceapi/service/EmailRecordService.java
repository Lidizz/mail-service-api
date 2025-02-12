package com.lidizz.mailserviceapi.service;

import com.lidizz.mailserviceapi.model.EmailRecord;
import com.lidizz.mailserviceapi.model.User;
import com.lidizz.mailserviceapi.repository.EmailRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmailRecordService {

    private final EmailRecordRepository emailRecordRepository;

    @Autowired
    public EmailRecordService(EmailRecordRepository emailRecordRepository) {
        this.emailRecordRepository = emailRecordRepository;
    }

    public List<EmailRecord> getAllEmails() {
        return emailRecordRepository.findAll();
    }

    public EmailRecord getEmailById(Long id) {
        Optional<EmailRecord> optionalEmail = emailRecordRepository.findById(id);
        return optionalEmail.orElseThrow(() -> new EmailNotFoundException("EmailRecord id: " + id + ", was not found."));
    }

    public List<EmailRecord> getAllBySender(User sender) {
        return emailRecordRepository.findAllBySender(sender);
    }

    public List<EmailRecord> getAllByRecipient(User recipient) {
        return emailRecordRepository.findAllByRecipient(recipient);
    }

    public EmailRecord createEmail(EmailRecord emailRecord) {
        return emailRecordRepository.save(emailRecord);
    }

    public void deleteEmailById(Long id) {
        emailRecordRepository.deleteById(id);
    }
}
