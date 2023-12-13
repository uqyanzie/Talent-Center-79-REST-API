package com.tujuhsembilan.app.repository;

import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
    public boolean existsByEmail(String email);
    
    public Optional<User> findByEmail(String email);

}
