package com.mockmate.backend.service;

import com.mockmate.backend.model.User;
import com.mockmate.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public String register(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            return "User already exists";
        }
        user.setId(UUID.randomUUID().toString());
        repository.save(user);
        return "User registered successfully";
    }

    public String login(String email, String password) {
        User user = repository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}
