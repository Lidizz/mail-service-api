package com.lidizz.mailserviceapi.service;

import com.lidizz.mailserviceapi.model.User;
import com.lidizz.mailserviceapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
            if (e instanceof DuplicateKeyException) {
                // A unique constraint was violated
                // Check which constraint was violated (username or email)
                if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                    throw new UsernameAlreadyExistsException("Username already exists: " + user.getUsername());
                } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                    throw new EmailAlreadyExistsException("EmailRecord already exists: " + user.getEmail());
                } else {
                    // Should not happen, but handle it just in case
                    throw new RuntimeException("Duplicate key violation, but could not determine which field.");
                }
            } else {
                // Some other data integrity violation occurred
                throw new RuntimeException("Error creating user: " + e.getMessage());
            }
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
