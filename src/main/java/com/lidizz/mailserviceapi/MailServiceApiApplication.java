package com.lidizz.mailserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MailServiceApiApplication implements CommandLineRunner {

    // Here I can add some logging of my data info, users and email
//    private static final Logger logger =
//            LoggerFactory.getLogger(MailServiceApiApplication.class);

//    private final EmailRecordRepository emailRepository;

//    public MailServiceApiApplication(EmailRecordRepository emailRepository) {
//        this.emailRepository = emailRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Here I can run some tests and print them to the console for quick testing
//        emailRepository.save(new EmailRecord("Theater", "carol@java.com", "jo@jay.no", "Hi jo!!"));
//        emailRepository.save(new EmailRecord("Theater", "carol@java.com", "jo@jay.no", "Hi jo!!"));
//        emailRepository.save(new EmailRecord("Theater", "carol@java.com", "jo@jay.no", "Hi jo!!"));

        // Fetch all senders and recipients and log to console
//        for (EmailRecord email : emailRepository.findAll()) {
//            logger.info("Sender: {}, Recipient: {}",
//                    email.getSender(), email.getRecipient());
//        }
    }
}
