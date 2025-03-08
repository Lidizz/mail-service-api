package com.lidizz.mailserviceapi.repository;

import com.lidizz.mailserviceapi.model.EmailRecord;
import com.lidizz.mailserviceapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRecordRepository extends JpaRepository<EmailRecord, Long> {

    // Find all emails by sender address
    List<EmailRecord> findAllBySender(User sender);

    // Find all emails by recipient address
    List<EmailRecord> findAllByRecipient(User recipient);

}
