package com.tujuhsembilan.app.repository;

import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{
    
}
