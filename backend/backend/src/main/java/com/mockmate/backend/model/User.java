package com.mockmate.backend.model;

import com.mockmate.backend.security.UserDetailsImpl;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter// Avoid using "user" as it's a reserved word
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Email(message = "Invalid sender email")
    @NotBlank(message = "Sender email is required")
    private String email;
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();
    @Column(name = "is_verified")
    private boolean isVerified = false;

}
