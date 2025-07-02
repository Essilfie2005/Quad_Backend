package com.quad.backend.service;

import com.quad.backend.model.User;
import com.quad.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 🔐 Hashing tool

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Register a user with hashed password
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 🔐 hash password
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());

                    // Only hash and update password if it's changed
                    if (!updatedUser.getPassword().equals(user.getPassword())) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }

                    user.setBio(updatedUser.getBio());
                    user.setProfileImage(updatedUser.getProfileImage());
                    return userRepository.save(user);
                }).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Authenticate using email + raw password with hashing
    public Optional<User> authenticateUser(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }
}