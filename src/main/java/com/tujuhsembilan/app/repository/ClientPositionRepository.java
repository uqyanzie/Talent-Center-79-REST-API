package com.tujuhsembilan.app.repository;

import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.ClientPosition;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface ClientPositionRepository extends JpaRepository<ClientPosition, UUID>{
    
    public ClientPosition findByClientPositionName(String clientPositionName);
}
