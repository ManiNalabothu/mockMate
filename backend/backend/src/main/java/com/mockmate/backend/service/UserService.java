package com.mockmate.backend.service;

import com.mockmate.backend.model.User;
import com.mockmate.backend.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public String register(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            return "User already exists";
        }
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

    public void updateUserSkills(String email, Set<String> skills) {
        User user = Optional.ofNullable(repository.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setSkills(new HashSet<>(skills));
        repository.save(user);
    }

    public List<User> getUsersBySkill(String skill) {
        return repository.findBySkillsContaining(skill)
                .stream()
                .filter(User::isVerified)
                .collect(Collectors.toList());
    }

    public boolean verifyUser(String email) {
        Optional<User> optionalUser = Optional.ofNullable(repository.findByEmail(email));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setVerified(true);
            repository.save(user);
            return true;
        }
        return false;
    }


}
