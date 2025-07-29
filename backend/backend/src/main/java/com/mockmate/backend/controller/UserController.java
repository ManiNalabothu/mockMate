package com.mockmate.backend.controller;

import com.mockmate.backend.dto.UpdateSkillsRequest;
import com.mockmate.backend.model.User;
import com.mockmate.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }

    @PutMapping("/user/update-skills")
    public ResponseEntity<String> updateSkills(@Valid @RequestBody UpdateSkillsRequest request) {
        service.updateUserSkills(request.getEmail(), request.getSkills());
        return ResponseEntity.ok("Skills updated successfully");
    }

    @GetMapping("/by-skill")
    public ResponseEntity<List<User>> getUsersBySkill(@RequestParam String skill) {
        List<User> users = service.getUsersBySkill(skill);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/user/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String email) {
        boolean updated = service.verifyUser(email);
        return updated ? ResponseEntity.ok("User verified successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
