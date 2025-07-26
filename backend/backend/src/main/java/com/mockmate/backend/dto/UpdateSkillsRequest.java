package com.mockmate.backend.dto;

import java.util.List;
import java.util.Set;

public class UpdateSkillsRequest {
    private String email;
    private Set<String> skills;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
