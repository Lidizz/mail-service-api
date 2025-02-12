package com.lidizz.mailserviceapi.util;

import com.lidizz.mailserviceapi.model.EmailRecord;
import com.lidizz.mailserviceapi.model.User;
import com.lidizz.mailserviceapi.repository.EmailRecordRepository;
import com.lidizz.mailserviceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EmailRecordRepository emailRecordRepository;

    @Autowired
    public MockData(UserRepository userRepository,
                    EmailRecordRepository emailRecordRepository) {
        this.userRepository = userRepository;
        this.emailRecordRepository = emailRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        emailRecordRepository.deleteAll();
        userRepository.deleteAll();

        // Create a small user base
//        User user1 = new User("KariVilFaktisk", "kari@vilikke.com", "password");
//        User user2 = new User("OleBolle", "ole@bolle.no", "password");
//        userRepository.saveAll(List.of(user1, user2));

        // Create a small email record base
//        EmailRecord email1 = new EmailRecord("Hello", "This is a test email", user1, user2);
//        EmailRecord email2 = new EmailRecord("Greeeetings!", "Hi! Meeting at 12 dude!!", user2, user1);
//        emailRecordRepository.saveAll(List.of(email1, email2));

        // Create a large user base
        List<User> users = List.of(
                new User("KariVilFaktisk", "kari@vilikke.com", "password"),
                new User("OleBolle", "ole@bolle.no", "password"),
                new User("LarsLiten", "lars@liten.no", "password"),
                new User("IngridBerg", "ingrid@berg.no", "password"),
                new User("ErikViking", "erik@viking.no", "password"),
                new User("JohnDoe", "john@doe.com", "password"),
                new User("JaneSmith", "jane@smith.com", "password"),
                new User("MichaelBrown", "michael@brown.com", "password"),
                new User("EmilyJones", "emily@jones.com", "password"),
                new User("WilliamTaylor", "william@taylor.com", "password"),
                new User("OliviaWilson", "olivia@wilson.com", "password"),
                new User("JamesJohnson", "james@johnson.com", "password"),
                new User("SophiaWilliams", "sophia@williams.com", "password"),
                new User("BenjaminDavis", "benjamin@davis.com", "password"),
                new User("EmmaMartinez", "emma@martinez.com", "password")
        );
        userRepository.saveAll(users);

        // Create a large email record base
        List<EmailRecord> emailRecords = List.of(
                new EmailRecord("Hello", "This is a test email", users.get(0), users.get(1)),
                new EmailRecord("Greetings!", "Hi! Meeting at 12 dude!!", users.get(1), users.get(0)),
                new EmailRecord("Project Update", "The project is on track.", users.get(2), users.get(3)),
                new EmailRecord("Lunch?", "Want to grab lunch at 1 PM?", users.get(4), users.get(5)),
                new EmailRecord("Weekend Plans", "What are your plans for the weekend?", users.get(6), users.get(7)),
                new EmailRecord("Important Notice", "Please review the attached document.", users.get(8), users.get(9)),
                new EmailRecord("Happy Birthday!", "Wishing you a fantastic birthday!", users.get(10), users.get(11)),
                new EmailRecord("Meeting Reminder", "Don't forget the meeting at 3 PM.", users.get(12), users.get(13)),
                new EmailRecord("New Policy", "Please read the new company policy.", users.get(14), users.get(0)),
                new EmailRecord("Feedback Request", "We value your feedback.", users.get(1), users.get(2)),
                new EmailRecord("Vacation Plans", "Let's plan our vacation!", users.get(3), users.get(4)),
                new EmailRecord("Job Offer", "We are pleased to offer you the position.", users.get(5), users.get(6)),
                new EmailRecord("Congratulations!", "Congratulations on your promotion!", users.get(7), users.get(8)),
                new EmailRecord("Team Building", "Join us for team building activities.", users.get(9), users.get(10)),
                new EmailRecord("New Product Launch", "Check out our new product!", users.get(11), users.get(12)),
                new EmailRecord("Holiday Schedule", "Here is the holiday schedule.", users.get(13), users.get(14)),
                new EmailRecord("Training Session", "Don't miss the training session tomorrow.", users.get(0), users.get(2)),
                new EmailRecord("Budget Approval", "Please approve the budget.", users.get(1), users.get(3)),
                new EmailRecord("Client Meeting", "We have a client meeting at 10 AM.", users.get(2), users.get(4)),
                new EmailRecord("New Hire", "Welcome our new team member!", users.get(3), users.get(5)),
                new EmailRecord("Performance Review", "Your performance review is scheduled.", users.get(4), users.get(6)),
                new EmailRecord("Office Party", "Join us for the office party!", users.get(5), users.get(7)),
                new EmailRecord("Deadline Reminder", "The deadline is approaching.", users.get(6), users.get(8)),
                new EmailRecord("New Software", "We are rolling out new software.", users.get(7), users.get(9)),
                new EmailRecord("Security Update", "Please update your passwords.", users.get(8), users.get(10)),
                new EmailRecord("Travel Plans", "Here are your travel details.", users.get(9), users.get(11)),
                new EmailRecord("Company Picnic", "Join us for the company picnic!", users.get(10), users.get(12)),
                new EmailRecord("New Benefits", "Check out the new employee benefits.", users.get(11), users.get(13)),
                new EmailRecord("Feedback Session", "We want to hear your feedback.", users.get(12), users.get(14)),
                new EmailRecord("New Office", "We are moving to a new office.", users.get(13), users.get(0)),
                new EmailRecord("Team Lunch", "Let's have a team lunch this Friday.", users.get(14), users.get(1)),
                new EmailRecord("New Policy", "Please review the new policy.", users.get(0), users.get(3)),
                new EmailRecord("Holiday Party", "Join us for the holiday party!", users.get(1), users.get(4)),
                new EmailRecord("New Project", "We are starting a new project.", users.get(2), users.get(5)),
                new EmailRecord("Training Materials", "Here are the training materials.", users.get(3), users.get(6)),
                new EmailRecord("Client Feedback", "Here is the feedback from the client.", users.get(4), users.get(7)),
                new EmailRecord("New Guidelines", "Please follow the new guidelines.", users.get(5), users.get(8)),
                new EmailRecord("Team Meeting", "We have a team meeting at 2 PM.", users.get(6), users.get(9)),
                new EmailRecord("New Feature", "Check out the new feature!", users.get(7), users.get(10)),
                new EmailRecord("Office Closure", "The office will be closed tomorrow.", users.get(8), users.get(11)),
                new EmailRecord("New Process", "We are implementing a new process.", users.get(9), users.get(12)),
                new EmailRecord("Survey", "Please complete the survey.", users.get(10), users.get(13)),
                new EmailRecord("New Hire", "Welcome our new team member!", users.get(11), users.get(14)),
                new EmailRecord("Performance Review", "Your performance review is scheduled.", users.get(12), users.get(0)),
                new EmailRecord("Office Party", "Join us for the office party!", users.get(13), users.get(1)),
                new EmailRecord("Deadline Reminder", "The deadline is approaching.", users.get(14), users.get(2))
        );
        emailRecordRepository.saveAll(emailRecords);
    }
}
