package com.mockmate.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UpdateSkillsRequest {
    private String email;
    private Set<String> skills;
}
