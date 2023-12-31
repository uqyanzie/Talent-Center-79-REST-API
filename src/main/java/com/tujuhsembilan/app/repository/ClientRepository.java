package com.tujuhsembilan.app.repository;

import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Client;
import com.tujuhsembilan.app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{
    
    Client findByEmail(String email);

    Client findByUser(User user);

    void deleteByEmail(String email);

}
