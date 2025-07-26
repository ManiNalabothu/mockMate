package com.mockmate.backend.repository.jpa;

import com.mockmate.backend.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.skills s WHERE LOWER(s) = LOWER(:skill)")
    List<User> findBySkillsContaining(@Param("skill") String skill);
}
