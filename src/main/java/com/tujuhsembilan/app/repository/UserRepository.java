package com.tujuhsembilan.app.repository;

import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Role;
import com.tujuhsembilan.app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
    boolean existsByEmail(String email);
    
    User findFirstByRole(Role role);
    
    Optional<User> findByEmail(String email);

    User findByUserId(UUID userId);

    void deleteByEmail(String email);
}
