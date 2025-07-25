package com.mockmate.backend.repository;

import com.mockmate.backend.model.User;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    public User findByEmail(String email) {
        return users.get(email);
    }

    public boolean existsByEmail(String email) {
        return users.containsKey(email);
    }
}
