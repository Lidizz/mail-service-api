package com.lidizz.mailserviceapi.service;

import com.lidizz.mailserviceapi.exception.EmailAlreadyExistsException;
import com.lidizz.mailserviceapi.exception.UserNotFoundException;
import com.lidizz.mailserviceapi.exception.UsernameAlreadyExistsException;
import com.lidizz.mailserviceapi.model.User;
import com.lidizz.mailserviceapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User with user-id: " + id + ", was not found."));
    }

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User with username: " + username + ", was not found."));
    }

    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User with email: " + email + ", was not found."));
    }

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Get the root cause
            SQLException sqlException = e.getRootCause() instanceof SQLException
                    ? (SQLException) e.getRootCause()
                    : null;

            if (sqlException != null && "23505".equals(sqlException.getSQLState())) {
                String message = sqlException.getMessage().toLowerCase();

                // Identify which is duplicate, if both are duplicate display the email
                if (message.contains("(email)=")) {
                    throw new EmailAlreadyExistsException(
                            "Email already exists: " + user.getEmail());
                } else if (message.contains("(username)=")) {
                    throw new UsernameAlreadyExistsException(
                            "Username already exists: " + user.getUsername());
                }
            }

            // Fallback for other data integrity issues
            throw new RuntimeException("Error creating user: " + e.getMessage());
        }
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id); // Include the UserNotFoundException if not found

        // Update fields only if they are not null
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
